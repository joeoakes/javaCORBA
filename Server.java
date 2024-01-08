import org.omg.CORBA.ORB;
import org.omg.CosNaming.*;
import HelloWorld.Hello;
import HelloWorld.HelloHelper;

public class Server {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            HelloImpl helloImpl = new HelloImpl();
            orb.connect(helloImpl);

            // Get the root naming context
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Bind the Hello object to the naming context
            String name = "Hello";
            NameComponent[] path = ncRef.to_name(name);
            ncRef.rebind(path, helloImpl);

            System.out.println("Server is ready...");
            orb.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
