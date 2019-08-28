package cn.jsmod2;


import cn.jsmod2.core.Application;
import cn.jsmod2.core.annotations.ServerApplication;
import cn.jsmod2.core.log.ServerLogger;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

/**
 * 结合junit测试框架使用该类，可以通过@RunWith(ServerRunner.class),使得启动
 * 测试框架同时开启服务器，使得可以直接进行测试
 * @author magiclu550
 * @see org.junit.runner.Describable
 * @see org.junit.runner.manipulation.Filterable
 * @see org.junit.runner.manipulation.Sortable
 * @see org.junit.runner.Runner
 * @see org.junit.runners.BlockJUnit4ClassRunner
 * @see org.junit.runners.ParentRunner
 */
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