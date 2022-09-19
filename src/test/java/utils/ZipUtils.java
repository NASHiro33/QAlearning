package utils;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

    public class ZipUtils {

        public void unzip(String source, String destination){
            unzip(source, destination, null);
        }

        public void unzip(String source, String destination, String password){
            try {
                ZipFile zipFile = new ZipFile(source);
                if (zipFile.isEncrypted()) {
                    zipFile.setPassword(password.toCharArray());
                }

                zipFile.extractAll(destination);
            } catch (ZipException e) {
                e.printStackTrace();
            }
        }
    }

