# SolvView

Visualizes the Riemannian manifolds Solv/Sol and Nil in real time using ray marching along geodesics.

## System requirements:

- Windows/Mac/Linux
- Java 8 or better
- OpenGL 3.3 or better

## Running:

To run this program, select the latest release [here](https://github.com/MagmaMcFry/SolvView/releases/) and download SolvView.jar, then simply run it.

If SolvView does not launch on Mac, try running the command `java -XstartOnFirstThread -jar SolvView.jar` from the directory containing SolvView.jar.

## Controls:

- **Up/down/left/right/Q/E** rotates the camera
- **W/A/S/D/Space/LShift** moves the camera
- **I/K** increases/decreases the number of raymarching steps (a higher value increases visibility but decreases framerate)
- **O/L** increases/decreases the maximum distance per raymarching step (a lower value improves the quality of geodesics but decreases visibility)
- **M** switches between Solv and Nil
- **Delete** quits the program

## Messing with the code:

Just clone this repository, and let Maven automatically fetch the necessary dependencies for you. Everything should work out of the box.

## Credits:

Thanks to the HyperRogue community for the inspiration, and to ZenoRogue in particular for his original Solv raytracer that inspired me to make a real-time shader-based clone.

Thanks to [LWJGL](https://www.lwjgl.org/) for making this project so easy.
