package fileeditor;

import fileeditor.ui.jwt.JwtEditor;

public class FileEditor {

   public static void main(String[] args) {
      new JwtEditor(args.length > 0 ? args[0] : "");
   }
}
