package nablarch.test.tool.findbugs.data.methodcall;

import nablarch.test.tool.findbugs.data.methodcall.chain.MethodChain;
import nablarch.test.tool.findbugs.data.methodcall.inherit.clazz.SubOfPublishedClassA;
import nablarch.test.tool.findbugs.data.methodcall.inherit.clazz.SubOfUnpublishedClassA;
import nablarch.test.tool.findbugs.data.methodcall.inherit.method.ClassC;
import nablarch.test.tool.findbugs.data.methodcall.inherit.pack.SubOfPublishedPackClassA;
import nablarch.test.tool.findbugs.data.methodcall.inherit.pack.SubOfUnpublishedPackClassA;
import nablarch.test.tool.findbugs.data.methodcall.interfaze.clazz.PublishedInterfaze;
import nablarch.test.tool.findbugs.data.methodcall.interfaze.clazz.PublishedInterfazeImple;
import nablarch.test.tool.findbugs.data.methodcall.interfaze.clazz.UnpublishedInterfaze;
import nablarch.test.tool.findbugs.data.methodcall.interfaze.clazz.UnpublishedInterfazeImple;
import nablarch.test.tool.findbugs.data.methodcall.interfaze.method.InterfazeMethod;
import nablarch.test.tool.findbugs.data.methodcall.interfaze.method.InterfazeMethodImpl;
import nablarch.test.tool.findbugs.data.methodcall.interfaze.pack.PublishedPackInterfazeImpl;
import nablarch.test.tool.findbugs.data.methodcall.interfaze.pack.UnpublishedPackInterfazeImpl;
import nablarch.test.tool.findbugs.data.methodcall.interfaze.published.pack.PublishedPackInterfaze;
import nablarch.test.tool.findbugs.data.methodcall.interfaze.unpublished.pack.UnpublishedPackInterfaze;
import nablarch.test.tool.findbugs.data.methodcall.methods.PublishedException;
import nablarch.test.tool.findbugs.data.methodcall.methods.PublishedMethods;
import nablarch.test.tool.findbugs.data.methodcall.methods.UnpublishedException;
import nablarch.test.tool.findbugs.data.methodcall.methods.UnpublishedMethods;
import nablarch.test.tool.findbugs.data.publishedapi.settings.data.java.interfaze.SubInterface;
import nablarch.test.tool.findbugs.data.publishedapi.settings.data.java.interfaze.SubInterfaceImple;

public class Caller {

    private PublishedMethods publishedMethods = new PublishedMethods();
    private UnpublishedMethods unpublishedMethods = new UnpublishedMethods();

    public void testParam() {
        String[] strs = { "aaa", "bbb" };
        strs.clone();
        publishedMethods.publishedMethodVariableParams("published", "published2");
        unpublishedMethods.unpublishedMethodVariableParams("unpublished", "unpublished2");
    }

    public void testChain() { // ????????????????????????????????????????????????
        MethodChain methodChain = new MethodChain();
        methodChain.testPublishedChain().testPublishedChain();
        methodChain.testUnublishedChain().testUnublishedChain();
    }

    public void testIf() { // if????????????????????????

        if (publishedMethods.publishedMethodBooleanReturn()) {
            publishedMethods.publishedMethodBooleanReturn();
            if (publishedMethods.publishedMethodBooleanReturn()) { // ????????????
                publishedMethods.publishedMethodBooleanReturn(); // ????????????
            }
        }

        if (unpublishedMethods.unpublishedMethodBooleanReturn()) {
            unpublishedMethods.unpublishedMethodBooleanReturn();
            if (unpublishedMethods.unpublishedMethodBooleanReturn()) { // ????????????
                unpublishedMethods.unpublishedMethodBooleanReturn(); // ????????????
            }
        }
    }

    public void testFor() { // for????????????????????????
        for (int i = 0; publishedMethods.publishedMethodBooleanReturn(); i++) {
            publishedMethods.publishedMethodBooleanReturn();
        }

        for (int i = 0; unpublishedMethods.unpublishedMethodBooleanReturn(); i++) {
            unpublishedMethods.unpublishedMethodBooleanReturn();
        }
    }

    public void testWhile() { // while????????????????????????
        while (publishedMethods.publishedMethodBooleanReturn()) {
            publishedMethods.publishedMethodBooleanReturn();
        }

        while (unpublishedMethods.unpublishedMethodBooleanReturn()) {
            unpublishedMethods.unpublishedMethodBooleanReturn();
        }
    }

    public void testDoWhile() { // do-while????????????????????????
        do {
            publishedMethods.publishedMethodBooleanReturn();
        } while (publishedMethods.publishedMethodBooleanReturn());

        do {
            unpublishedMethods.unpublishedMethodBooleanReturn();
        } while (unpublishedMethods.unpublishedMethodBooleanReturn());
    }

    public void testSwitch() { // switch????????????????????????

        switch (publishedMethods.publishedMethodIntReturn()) {
        case 2:
            publishedMethods.publishedMethodIntReturn();
            break;

        default:
            break;
        }

        switch (unpublishedMethods.unpublishedMethodIntReturn()) {
        case 1:
            unpublishedMethods.unpublishedMethodIntReturn();
            break;

        default:
            break;
        }
    }

    public void testTernaryOperator() { // ?????????????????????????????????
        String test = publishedMethods.publishedMethodBooleanReturn() ? "aaa" : "bbbb";
        String test2 = unpublishedMethods.unpublishedMethodBooleanReturn() ? "aaaa" : "bbb";

        // ????????????????????????
        System.out.println(test);
        System.out.println(test2);
    }

    public int testReturn() { // return????????????????????????
        if (publishedMethods.publishedMethodBooleanReturn()) {
            return publishedMethods.publishedMethodIntReturn();
        } else {
            return unpublishedMethods.unpublishedMethodIntReturn();
        }
    }

    public void testCatchAndFinally() { // catch??????finally????????????????????????
        try {
            if (publishedMethods.publishedMethodBooleanReturn()) {
                throw new PublishedException();
            } else {
                throw new UnpublishedException();
            }
        } catch (PublishedException e) {

        } catch (UnpublishedException e) {

        } finally {
            publishedMethods.publishedMethodBooleanReturn();
            unpublishedMethods.unpublishedMethodBooleanReturn();
        }
    }

    public void testInheritance() { // ??????????????????????????????

        // ????????????????????????
        ClassC classC = new ClassC();
        // ???????????????????????????
        // ??????????????????
        classC.publishedPublishedOverriddenMethod();
        classC.publishedUnpublishedOverriddenMethod();
        // ??????????????????
        classC.unpublishedPublishedOverriddenMethod();
        classC.unpublishedUnpublishedOverriddenMethod();
        // ???????????????????????????
        classC.publishedMethodA();
        classC.unpublishedMethodA();

        // ?????????????????????
        SubOfUnpublishedClassA subOfUnpublishedClassA = new SubOfUnpublishedClassA();
        subOfUnpublishedClassA.methodA();
        SubOfPublishedClassA subOfPublishedClassA = new SubOfPublishedClassA();
        subOfPublishedClassA.methodA();

        // ?????????????????????
        SubOfUnpublishedPackClassA subOfUnpublishedPackClassA = new SubOfUnpublishedPackClassA();
        subOfUnpublishedPackClassA.methodA();
        SubOfPublishedPackClassA subOfPublishedPackClassA = new SubOfPublishedPackClassA();
        subOfPublishedPackClassA.methodA();

    }

    public void testInterface() {
        // ??????????????????
        InterfazeMethod interfazeMethod = new InterfazeMethodImpl();
        interfazeMethod.publishedInterfaceMethod();
        interfazeMethod.unpublishedInterfaceMethod();

        // ???????????????????????????
        PublishedInterfaze publishedInterfaze = new PublishedInterfazeImple();
        publishedInterfaze.method();
        UnpublishedInterfaze unpublishedInterfazeImple = new UnpublishedInterfazeImple();
        unpublishedInterfazeImple.method();

        // ?????????????????????
        PublishedPackInterfaze publishedPackInterfaze = new PublishedPackInterfazeImpl();
        publishedPackInterfaze.methodA();
        UnpublishedPackInterfaze unpublishedPackInterfaze = new UnpublishedPackInterfazeImpl();
        unpublishedPackInterfaze.methodA();

        // ???????????????
        InterfazeMethod annonyouse = new InterfazeMethod() {

            public void unpublishedInterfaceMethod() {
            }

            public void publishedInterfaceMethod() {
            }
        };
        annonyouse.publishedInterfaceMethod();
        annonyouse.unpublishedInterfaceMethod();

        SubInterface subInterface = new SubInterfaceImple();
        subInterface.subPublishedInterfaceMethod();
        subInterface.subUnpublishedInterfaceMethod();
        subInterface.superPublishedInterfaceMethod();
        subInterface.superUnpublishedMethod();

    }
}
