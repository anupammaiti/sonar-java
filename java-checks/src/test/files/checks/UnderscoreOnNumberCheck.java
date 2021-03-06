class A {
  private static final long serialVersionUID = -8778479691651900590L;

  int i0 = 1000; // Compliant
  int i1 = 10000000; // Noncompliant {{Add underscores to this numeric value for readability}}
  int i2 = 10_000_000; // Compliant

  int  b1 = 0b01101001010011011110010101011110; // Noncompliant
  int  b2 = 0b011010010100110_111_10010101011110; // Compliant - I put underscore where I want!

  long l1 = 0x7fffffffffffffffL; // Noncompliant
  long l2 = 0x7fff_ffff_ffff_ffffL;

  void foo(int x) {
    if (x < -8778479691651900590L) {} // Noncompliant
  }
}
