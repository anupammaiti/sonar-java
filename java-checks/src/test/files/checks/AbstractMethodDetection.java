import java.lang.String;

class A{
  void method(int a){}
  void method(String a){}
  void method(String[] a){}
  void method2(int a);
}

class B{
  void foo(){
    A a = new A();
    a.method(1);
    a.method("");
    a.method(new String[]{""});
    a.method2(1);
  }
}