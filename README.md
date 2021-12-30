# Democube

Done as a first year student, with lots of spaghetti code. 
It uses a library called O1lib based on Scala Swing that we got to use in the course, abstracting away tickrates and drawing lines on the window. Everything else is built from scratch, using lots of matrix algebra for rotations and bounce calculations etc. 

## How to run
With `sbt` installed, you can type `sbt run` in a terminal while in the root of this project. 

```
src
└── main
    └── scala
        └── DemoCube
            ├── Camera.scala
            ├── Constants.scala
            ├── Corner.scala
            ├── Cube.scala
            ├── Demo.scala      <- actual runnable object
            ├── Line.scala
            ├── Point.scala
            └── Vec.scala
```
Movement is very janky: use WASD to move around, while Q and E turn the camera left and right. 

![Gif](https://github.com/peik-etzell/demo-cube/blob/master/pictures/demo.gif)
