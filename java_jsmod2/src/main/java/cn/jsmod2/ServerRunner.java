package cn.jsmod2;

import cn.jsmod2.core.Application;
import cn.jsmod2.core.annotations.ServerApplication;
import cn.jsmod2.core.log.ServerLogger;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

@ServerApplication(DefaultServer.class)
public class ServerRunner extends BlockJUnit4ClassRunner {

    private Class testClass;


    public ServerRunner(Class testClass) throws InitializationError {
        super(testClass);
        this.testClass = testClass;

    }


    @Override
    protected Statement withBefores(FrameworkMethod method, Object target, Statement statement) {

        return new Statement() {
            @Override
            public void evaluate() throws Throwable{
                if(method.getAnnotation(ServerTest.class)!=null){
                    ServerLogger.getLogger().info("[Start the Server...{TEST}]");
                    Application.runTest(ServerRunner.class,new String[0]);
                }
                method.invokeExplosively(testClass.newInstance());
            }
        };
    }
}