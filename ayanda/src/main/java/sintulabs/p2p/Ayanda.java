package sintulabs.p2p;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.net.wifi.p2p.WifiP2pDevice;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by sabzo on 1/19/18.
 */

public class Ayanda {
    private Bluetooth bt;
    private Lan lan;
    private WifiDirect wd;

    private Context context;

    /**
     * Ayanda is a class that discovers and interacts with nearby devices that support
     * Network Service Discovery (NSD), WiFi Direct, and  Bluetooth
     * @param context The Activity/Application Context
     * @param iBluetooth An interface to handle Bluetooth events
     * @param iLan An interface to handle LAN (NSD/Bonjour/ZeroConfig/etc.,) events
     * @param iWifiDirect An interface to handle Wifi Direct events
     */
    public Ayanda(Context context, IBluetooth iBluetooth, ILan iLan, IWifiDirect iWifiDirect) {
        this.context = context;
        bt = new Bluetooth(context, iBluetooth);
        lan = new Lan(context, iLan);
        wd = new WifiDirect(context, iWifiDirect);
    }

    /**
     * Discover nearby devices that have made themselves detectable via blue Bluetooth.
     * Discovered devices are stored in a collection of devices found.
     */
    public void btDiscover() {
        bt.discover();
    }

    /**
     * Connects to a discovered bluetooth device. Role: Client
     * @param device Bluetooth Device
     */
    public void btConnect(BluetoothDevice device) {
       bt.connect(device);
    }

    /**
     * Register a Bluetooth Broadcast Receiver.
     * This method must be called to detect Bluetooth events. The iBluetooth interface
     * exposes Bluetooth events.
     */
    public void btRegisterReceivers() {
        bt.registerReceivers();
    }

    /**
     * Unregisters Bluetooth Broadcast Receiver.
     * Must be called when Activity/App stops/closes
     */
    public void btUnregisterReceivers() {
        bt.unregisterReceivers();
    }

    /**
     * Announce device's presence via Bluetooth
     */
    public void btAnnounce() {
        bt.announce();
    }

    /**
     * Get the names of the Bluetooth devices discovered
     * @return
     */
    public Set<String> btGetDeviceNamesDiscovered() {
        return bt.getDeviceNamesDiscovered();
    }

    public HashMap<String, BluetoothDevice> btGetDevices() {
        return bt.getDeviceList();
    }

    /**
     * Send data from this device to a connected bluetooth device
     * @param device
     * @param bytes
     */
    public void btSendData(BluetoothDevice device, byte[] bytes) throws IOException {
        bt.sendData(device, bytes);
    }


    public void lanShare (NearbyMedia media) throws IOException {
        lan.shareFile(media);
    }

    public void lanAnnounce() {
        lan.announce();
    }
    /*
        Discover nearby devices using LAN:
        A device can register a service on the network and other devices connected on the network
        will be able to detect it.
     */
    public void lanDiscover() {
        lan.discover();
    }

    public void lanStopAnnouncement() {
        lan.stopAnnouncement();
    }

    public void lanStopDiscovery() {
        lan.stopDiscovery();
    }
    public List<Lan.Device> lanGetDeviceList() {
      return lan.getDeviceList();
    }

    /* Wifi Direct Methods */

    /**
     *
     * @param device to send data to
     * @param bytes array of data to send
     */
    public void wdSendData(WifiP2pDevice device, byte[] bytes) {
        wd.sendData(device, bytes);
    }

    public void wdShareFile (NearbyMedia media) throws IOException {
        wd.shareFile(media);
    }

  
    /**
     * Connect to a WifiDirect device
     * @param device
     */
    public void wdConnect(WifiP2pDevice device) {
        wd.connect(device);
    }

    /**
     * Discover nearby WiFi Direct enabled devices
     */
    public void wdDiscover() {
        wd.discover();
    }

    public void wdRegisterReceivers() {
        wd.registerReceivers();
    }

    public void wdUnregisterReceivers() {
        wd.unregisterReceivers();
    }

    public ArrayList<WifiP2pDevice> wdGetDevicesDiscovered() {
        return wd.getDevicesDiscovered();
    }
}
