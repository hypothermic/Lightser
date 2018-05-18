# Lightser
Lightweight Java library to make asynchronous serialization easier.

## Example
```java
int[] object = new int[] {0, 3, 6, 9};

Lightser.serialize(object, sample, new SerializeCallback() {
    public void onSerializeSuccessful() {
        System.out.println("Serialize success");
    }
    public void onSerializeFailed(Exception x) {
        x.printStackTrace();
    }
});

Lightser.deserialize(sample, new DeserializeCallback() {
    public void onDeserializeSuccessful(Serializable ser) {
        if (Arrays.toString((int[]) ser).equals("[0, 3, 6, 9]")) {
            System.out.println("Deserialize success");
        }
    }
    public void onDeserializeFailed(Exception x) {
        x.printStackTrace();
    }
});
```

## Maven dependency
```xml
<repository>
  <id>lightser-mvn-repo</id>
  <url>https://raw.github.com/hypothermic/lightser/mvn-repo/</url>
</repository>

<dependency>
  <groupId>nl.hypothermic</groupId>
  <artifactId>lightser</artifactId>
  <!-- If you're using Maven3, specify version tag -->
  <version>LATEST</version>
</dependency>
```

## Gradle dependency
```gradle
repositories {
    maven {
        url "https://raw.github.com/hypothermic/lightser/mvn-repo/"
    }
}

dependencies {
    // Note: use 'api' instead of 'compile' if you're using Android Studio.
    compile group: 'nl.hypothermic', name: 'lightser', version: '1.0.0-RELEASE'
}
```