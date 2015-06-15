# MESH
Ubudu mesh SDK for Android allows devices to establish a connection to a mesh network and to send messages through it.

## System and hardware requirements
Any Android device with Bluetooth 4.0 and Android 4.3 or higher.

## Adding MESH to mobile application

Note that Ubudu Mesh is in early stage of development and it might change (including API changes).

### Adding Ubudu SDK
Add Ubudu SDK to the project. If using Android Studio [check AS instructions](https://github.com/Ubudu/Android-SDK/blob/master/studio/README.md). If using Eclipse [check Eclipse instructions](https://github.com/Ubudu/Android-SDK/blob/master/eclipse/README.md)

### Usage instructions

The UbuduSDK instance provides method to obtain the mesh manager.
```UbuduMeshManager meshManager = sdk.getMeshManager()```.
Note that mesh manager class doesn't inheritate from com.ubudu.sdk.UbuduAreaManager like Beacon or Geofence manager. 

#### Start / Stop mesh manager
To start using mesh ```UbuduMeshManager``` must be started. It may be done by calling:
```meshManager.start()```

```start()```  method starts searching for connectable, mesh enabled beacons.

To stop mesh manager and prevent searching for mesh beacons call ```stop()``` nwthod.

#### Sending message through mesh
Mesh is super easy to use. Just call:

```public void sendMeshMessage(String meshMessage, Integer meshId, String networkUUID);```

- **MeshMessage** is a string with mesh message to be send. It should be no longer than 16 bytes.

- **meshId** is the device address in dec format. Should be between 1-32767.

- **networkUUID** String with UUID of the mesh network UUID. ```meshId``` should be part of the network.

#### Receiving status of the message
```UbuduMeshDelegate``` interface must be implemented to get feedback from mesh manager. At the moment receiving status is possible by method: ```public void onSendMeshMessage(int status);
```. This method is called by mesh manager after sending a message. status values can be:

```
MESH_MESSAGE_STATUS_OK = 0;				// Message sent with success
MESH_MESSAGE_STATUS_FAIL = 1;			// Sending message failed - other reason
MESH_MESSAGE_STATUS_FAIL_TIMEOUT = 2;	// Sending message failed because of timeout
MESH_MESSAGE_STATUS_FAIL_NO_MESH = 3;	// Sending message failed because of missing Mesh service
MESH_MESSAGE_STATUS_NO_MESH_NODE = 4;	// Sending message failed because of no nearby mesh connectable node
MESH_MESSAGE_STATUS_TOO_LONG = 5;		// Sending message failed because of too long message
```

#### Receiving feedback about nearby mesh nodes
```UbuduMeshDelegate``` allows you to be notified about number of nearby availble mesh connectable nodes by ```public void onUpdateVisibleAndConnectableNodes(ArrayList<UbuduMeshNode>nodes);```.