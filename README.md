# Javux
Java library to do things on Linux

Script examples
```java
// Python 3 example
Script pythonScript = new Script(ScriptInterpreter.PYTHON3);
pythonScript.addLine("print(\"hi\")");
System.out.println(pythonScript.exec().getOutput());

// ZSH example
Script zshScript = new Script(ScriptInterpreter.ZSH);
zshScript.addLine("echo hi");
System.out.println(zshScript.exec().getOutput());

// SH (default) example
Script script = new Script();
script.addLine("echo hi");
System.out.println(script.exec().getOutput());
```
