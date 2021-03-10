package pcrevice;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import processing.data.JSONObject;
import processing.data.JSONArray;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author Loïc Vital
 *
 */

public abstract class PCreviceApplet extends PApplet {

	protected PImage loadSurface(String filename) {
		PImage surface = loadImage(filename);
		return surface;
	}

	protected List<LineSegment> loadLines(String filename) {
		List<LineSegment> lines = new LinkedList<LineSegment>();
		
		JSONObject obj = loadJSONObject(filename);

		JSONArray arr = obj.getJSONArray("lines");
		for(int i=0; i<arr.size(); i++) {
			JSONObject lineObj = arr.getJSONObject(i);
			LineSegment seg = new LineSegment(new PVector(lineObj.getFloat("x1"), lineObj.getFloat("y1")), new PVector(lineObj.getFloat("x2"), lineObj.getFloat("y2")));
			lines.add(seg);
		}

		return lines;
	}

	protected Network loadNetwork(String filename) {
		Network net = new Network();
		
		JSONObject obj = loadJSONObject(filename);

		JSONArray inNodesArr = obj.getJSONArray("inNodes");
		for(int i=0; i<inNodesArr.size(); i++) {
			Node node = parseNodeObj(inNodesArr.getJSONObject(i));
			net.addNode(node);
		}

		JSONArray outNodesArr = obj.getJSONArray("outNodes");
		for(int i=0; i<outNodesArr.size(); i++) {
			Node node = parseNodeObj(outNodesArr.getJSONObject(i));
			net.addNode(node);
		}

		JSONArray edgeArr = obj.getJSONArray("edges");
		for(int i=0; i<edgeArr.size(); i++) {
			Edge edge = parseEdgeObj(edgeArr.getJSONObject(i), net);
			net.addEdge(edge);
		}

		return net;
	}

	protected Skeleton loadSkeleton(String filename, Network net) {
		Skeleton skeleton = new Skeleton();

		JSONObject obj = loadJSONObject(filename);

		skeleton.rootNode = parseNodeObj(obj.getJSONObject("rootNode"));
		skeleton.rootEdge = parseRootEdgeObj(obj.getJSONObject("rootEdge"), net, skeleton);

		JSONArray childrenMapArr = obj.getJSONArray("childrenMap");
		for(int i=0; i<childrenMapArr.size(); i++) {
			JSONObject entryObj = childrenMapArr.getJSONObject(i);
			int edgeId = entryObj.getInt("edgeId");
			List<Integer> childrenIds = new LinkedList<Integer>();
			JSONArray childrenArr = entryObj.getJSONArray("children");
			for(int j=0; j<childrenArr.size(); j++) {
				childrenIds.add(childrenArr.getInt(j));
			}
			skeleton.addChildrenIds(edgeId, childrenIds);
		}

		return skeleton;
	}

	private Node parseNodeObj(JSONObject nodeObj) {
		AffineElement elt = new AffineElement(new PVector(nodeObj.getFloat("x"), nodeObj.getFloat("y")), nodeObj.getFloat("angle"));
		Node node = new Node(nodeObj.getInt("id"), elt);
		return node;
	}

	private Edge parseEdgeObj(JSONObject edgeObj, Network net) {
		Node source = net.getNode(edgeObj.getInt("sourceId"));
		Node target = net.getNode(edgeObj.getInt("targetId"));
		Edge edge = new Edge(edgeObj.getInt("id"), source, target);
		String geo = edgeObj.getString("geo");
		if(geo.equals("line")) {
			edge.setGeoLine();
		} else {
			PVector ctrl = AffineElement.intersection(source.elt, target.elt);
			edge.setGeoParabola(ctrl);
		}
		return edge;
	}

	private Edge parseRootEdgeObj(JSONObject edgeObj, Network net, Skeleton skeleton) {
		Node source = skeleton.rootNode;
		Node target = net.getNode(edgeObj.getInt("targetId"));
		Edge edge = new Edge(edgeObj.getInt("id"), source, target);
		PVector ctrl = AffineElement.intersection(source.elt, target.elt);
		edge.setGeoParabola(ctrl);
		return edge;
	}

}
