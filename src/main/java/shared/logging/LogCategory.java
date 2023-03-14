package shared.logging;

public enum LogCategory {

    SYSTEM("System"),
    UI("UI"),
    OTHER("Other")
    ;

    private String folderName;

    LogCategory(String folderName) {
        this.folderName = folderName;
    }

    public String getFolderName() {
        return folderName;
    }
}
