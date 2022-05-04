# **Parking Access Control w/ computer vision**

<img src="./resources/car_plate.jpeg" align="right"
      width="150" height="100%">

The idea behind this project is to be able to implement a parking access control system with license plate recognition in any established parking access control, and also be able to manage it remotely

* **React JS** cross-platform GUI
* **Spring Boot (Kotlin)** to be able to deploy the server on any OS
* **OpenAlpr** C++ library for license plate detection system
#



### **Current development status**
#### User GUI
<img src="./resources/example-ui.jpg" width="550" height="100%">

#### Detect plate and activate relay using smartphone as IP camera
<img src="./resources/full.gif" width="550" height="100%">

#### Example UI
<img src="./resources/add.gif" width="550" height="100%">

### **How to deploy**

### OpenAlpr
Build the alpr from the source (only linux): 

      git clone https://github.com/ngomezcn/Parking-Access-Control-w-computer-vision
      cd Parking-Access-Control-w-computer-vision/src/alpr/src/
      mkdir build/
      cmake -DCMAKE_INSTALL_PREFIX:PATH=/usr -DCMAKE_INSTALL_SYSCONFDIR:PATH=/etc ..
      make
      sudo make install


