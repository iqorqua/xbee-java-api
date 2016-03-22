# Getting Started #

## Using the configuration application ##

  * Download xbee-java-api X.X.jar
  * Get log4j (http://logging.apache.org/log4j/1.2/)
  * Install RXTX (http://users.frii.com/jarvi/rxtx/download.html)
  * Run with "java -jar xbee-java-api X.X.jar"

## Using the API ##

Using the API is straightforward. In this example the node identifier of the module is printed:

```
public class SimpleExample1 {

    private static final Logger logger = Logger.getLogger(SimpleExample1.class);

    public static void main(String[] args) throws XBeeOperationFailedException {
        BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.ERROR);

        XBee xbee = new XBeeFactory("/dev/ttyUSB0").newXBee();

        System.out.println("Node identifier is \"" + xbee.getNodeIdentifier() + "\"");

        xbee.disconnect();
    }
}
```