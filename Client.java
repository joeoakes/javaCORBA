import org.omg.CORBA.ORB;
import org.omg.CosNaming.*;
import HelloWorld.Hello;
import HelloWorld.HelloHelper;

public class Client {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);

            // Get the root naming context
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Resolve the Hello object from the naming context
            String name = "Hello";
            Hello hello = HelloHelper.narrow(ncRef.resolve_str(name));

            System.out.println("Client received: " + hello.sayHello());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
