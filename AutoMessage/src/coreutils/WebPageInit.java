package coreutils;

import org.openqa.selenium.support.pagefactory.Annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public abstract class WebPageInit {

    private static Object object;
    private static Class<?> cls;

    private static void init(Field f) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Annotation[] annotations = f.getAnnotations();
        for (Annotation annotation : annotations) {
            String annotationNameWithPath = annotation.annotationType().getName();
            String annotationName = annotationNameWithPath.substring(annotationNameWithPath.lastIndexOf(".") + 1);
            switch (annotationName) {
            case "FindBy":
                Object id = new Annotations(f).buildBy();
                Constructor<?>[] constructors = f.getType().getConstructors();
                for (Constructor<?> constructor : constructors) {
                    if (constructor.getParameterCount() == 1) {
                        f.set(object, constructor.newInstance(id));
                        break;
                    }
                }
                break;
            }
        }
    }

    static void initElements(Object obj) {
        object = obj;
        cls = obj.getClass();
        do {
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                if (field.getModifiers() == 1) {
                    try {
                        init(field);
                    } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                        e.printStackTrace();
                    }
                }
            }
            cls = cls.getSuperclass();
        } while (!cls.getName().endsWith("utils.WebPageInit"));
    }

    public WebPageInit() {
        initElements(this);
    }
}
