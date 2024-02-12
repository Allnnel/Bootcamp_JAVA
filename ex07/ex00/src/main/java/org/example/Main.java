package org.example;

import static java.lang.System.out;

import classes.Car;
import classes.User;
import java.lang.reflect.*;
import java.util.Scanner;

public class Main {
  Scanner scanner;

  public static void main(String[] args) throws NoSuchMethodException {
    Main main = new Main();
    Object[] classes = new Object[2];
    classes[0] = new Car();
    classes[1] = new User();

    main.scanner = new Scanner(System.in);
    out.println("Classes:");
    for (Object c : classes) {
      out.println(c.getClass().getSimpleName());
    }
    out.println("---------------------");
    out.println("Enter class name:");
    String className = main.scanner.next();
    if (className.equals("Car") || className.equals("car")) {
      main.printObjectInfo(classes[0]);
      Object obj = main.createObject(classes[0]);
      assert obj != null;
      main.toStringObj(obj);
      main.changeField(obj);
      main.toStringObj(obj);
      main.callMethod(obj);
    } else if (className.equals("User") || className.equals("user")) {
      main.printObjectInfo(classes[1]);
      Object obj = main.createObject(classes[1]);
      assert obj != null;
      main.toStringObj(obj);
      main.changeField(obj);
      main.toStringObj(obj);
      main.callMethod(obj);
    } else {
      out.println("---------------------");
      out.println("No such class exists");
    }
  }

  private void callMethod(Object obj) {
    out.println("---------------------");
    out.println("Enter name of the method for call:");
    String methodSignature = scanner.next();

    String[] parts = methodSignature.split("\\(");
    String methodName = parts[0];
    String paramType = parts[1].substring(0, parts[1].length() - 1);

    try {
      Class<?> parameterClass = null;

      if (paramType.equals("int")) {
        parameterClass = int.class;
      } else if (paramType.equals("String")) {
        parameterClass = String.class;
      }

      Method method;
      if (parameterClass == int.class) {
        method = obj.getClass().getDeclaredMethod(methodName, parameterClass);
      } else {
        method = obj.getClass().getDeclaredMethod(methodName);
      }

      if (method.getParameterCount() > 0) {
        out.println("Enter int value:");
        Object paramValue = null;
        if (parameterClass == int.class) {
          paramValue = scanner.nextInt();
        } else if (parameterClass == String.class) {
          paramValue = scanner.next();
        }
        Object result = method.invoke(obj, paramValue);
        out.println("Method returned:");
        out.println(result);
      } else {
        Object result = method.invoke(obj);
        out.println("Method returned:");
        out.println(result);
      }

    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      out.println(e);
    }
  }

  public void changeField(Object obj) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("---------------------");
    System.out.println("Enter name of the field for changing:");
    String fieldName = scanner.next();

    try {
      Field field = obj.getClass().getDeclaredField(fieldName); // Получаем поле по имени
      field.setAccessible(true); // Разрешаем доступ к приватным полям

      System.out.println("Enter new value for the field:");
      if (field.getType() == String.class) {
        String newValue = scanner.next();
        field.set(obj, newValue); // Обновляем значение поля
      } else if (field.getType() == int.class) {
        int newValue = scanner.nextInt();
        field.set(obj, newValue); // Обновляем значение поля
      } else {
        System.out.println("Unsupported field type");
      }
    } catch (NoSuchFieldException | IllegalAccessException e) {
      out.print(e);
    }
  }

  private Object createObject(Object obj) throws NoSuchMethodException {
    out.println("---------------------");
    out.println("Let’s create an object.");
    out.println("firstName:");
    String firstName = scanner.next();
    out.println("lastName:");
    String lastName = scanner.next();
    out.println("height:");
    int height = scanner.nextInt();
    Constructor<?>[] constructors = obj.getClass().getDeclaredConstructors();
    Parameter[] parameters = null;
    for (Constructor<?> constructor : constructors) {
      if (constructor.getParameterCount() > 0) {
        parameters = constructor.getParameters();
        Object[] constructorArgs = new Object[parameters.length];
        constructorArgs[0] = firstName;
        constructorArgs[1] = lastName;
        constructorArgs[2] = height;
        try {
          // Создание объекта используя найденный конструктор и переданные аргументы
          Object newObject = constructor.newInstance(constructorArgs);
          return newObject;
        } catch (InstantiationException
            | IllegalAccessException
            | IllegalArgumentException
            | InvocationTargetException e) {
          e.printStackTrace();
        }
      }
    }
    return null;
  }

  private void toStringObj(Object obj) {
    java.lang.reflect.Field[] fields = obj.getClass().getDeclaredFields();
    out.print("Object created: " + obj.getClass().getSimpleName() + "[");

    int commaCounter = 0;
    for (java.lang.reflect.Field field : fields) {
      field.setAccessible(true); // Игнорируем модификаторы доступа
      try {
        out.print(field.getName() + "=" + field.get(obj));
        if (commaCounter < fields.length - 1) {
          out.print(", ");
        }
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
      commaCounter++;
    }

    out.println("]");
  }

  private void printObjectInfo(Object obj) {
    out.println("---------------------");
    infoFiled(obj);
    infoMethods(obj);
  }

  private void infoFiled(Object obj) {
    out.println("fields:");
    java.lang.reflect.Field[] fields = obj.getClass().getDeclaredFields();
    for (Field field : fields) {
      out.println("   " + field.getType().getSimpleName() + " " + field.getName());
    }
  }

  private void infoMethods(Object obj) {
    out.println("methods:");
    java.lang.reflect.Method[] methods = obj.getClass().getDeclaredMethods();
    for (Method method : methods) {

      out.print("   " + method.getReturnType().getSimpleName() + " " + method.getName() + "(");
      java.lang.reflect.Type[] types = method.getGenericParameterTypes();
      int commaCounter = 0;
      for (Type type : types) {
        out.print(type.getTypeName());
        commaCounter++;
        if (commaCounter < types.length - 1) {
          out.print(",");
        }
      }
      out.print(");\n");
    }
  }
}
