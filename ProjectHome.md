A Java API for XBee.

Implemented according to the "XBee®/XBee-PRO® DigiMeshTM 2.4 RF Modules" manual.


Also provided is an application to configure XBee devices (see [Screenshoots](Screenshoots.md)):
![http://xbee-java-api.googlecode.com/svn/trunk/images/Screenshot-1.png](http://xbee-java-api.googlecode.com/svn/trunk/images/Screenshot-1.png)


The api is very simple to use, in this example the node identifier is printed to output:
```
public class SimpleExample1 {

    public static void main(String[] args) throws XBeeOperationFailedException {

        XBee xbee = new XBeeFactory("/dev/ttyUSB0").newXBee();

        System.out.println("Node identifier is \"" + xbee.getNodeIdentifier() + "\"");

        xbee.disconnect();
    }
}
```

If you found a bug, need additional functionality, or anything else **don't hesitate to contact me at: davidmiguel { at } antunes.net**!