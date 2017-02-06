package by.vadzimnovikau1.module7

class ValuesFromProperties {
    private static Properties props = new Properties()
    private static String idValue

    static String getID() {
        if (idValue == null) {
            File propsFile = new File(System.getProperty("user.dir") + "\\config.properties")
            props.load(propsFile.newDataInputStream())
            idValue = props.getProperty("id")
        }
        idValue
    }
}
