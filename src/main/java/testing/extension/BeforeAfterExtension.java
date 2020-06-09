package testing.extension;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class BeforeAfterExtension implements BeforeEachCallback, AfterEachCallback {


    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        System.out.println("Inside Before each extension");
    }


    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        System.out.println("Inside After each extension");

    }

}