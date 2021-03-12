# PCrevice

PCrevice is a Processing API meant to give access to models created with the Crevice GUI in a Processing sketch.

## Installation

The jar file containing the library is stored in the **output** directory.
If you want to use the API in a Processing sketch using the Processing IDE, one way to make it work is to create a **code** directory in your sketch folder and place the PCrevice jar in it.

## Usage

Models created with the Crevice GUI are stored in *json* format, and each file stores a different stage of the modeling process (lines, network and skeleton).
Use the **CreviceReader** class to read these files and create the corresponding objects, then do whataver you like with them.
You may want to refer to the documentation, stored in the folder **apidoc**.
