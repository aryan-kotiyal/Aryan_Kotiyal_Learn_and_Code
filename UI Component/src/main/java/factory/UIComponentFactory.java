package factory;

import component.Button;
import component.Checkbox;
import component.TextField;

public interface UIComponentFactory {
    Button createButton();
    Checkbox createCheckbox();
    TextField createTextField();
}
