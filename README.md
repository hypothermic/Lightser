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
        if (Arrays.toString((int[]) ser).equals("[0, 2, 4, 8]")) {
            System.out.println("Deserialize success");
        }
    }
    public void onDeserializeFailed(Exception x) {
        x.printStackTrace();
    }
});
```