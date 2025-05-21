package factory;

import component.*;

public class MacOSUIFactory implements UIComponentFactory {
    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacOSCheckbox();
    }

    @Override
    public TextField createTextField() {
        return new MacOSTextField();
    }
}
