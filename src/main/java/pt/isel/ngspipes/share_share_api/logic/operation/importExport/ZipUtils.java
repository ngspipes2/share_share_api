package pt.isel.ngspipes.share_share_api.logic.operation.importExport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

    public static void zip(String sourceFolder, String result) throws IOException {
        Collection<String> fileTree = getFilesTree(sourceFolder);

        try(FileOutputStream fos = new FileOutputStream(result)) {
            try(ZipOutputStream zos = new ZipOutputStream(fos)) {
                for (String file: fileTree) {
                    ZipEntry entry = new ZipEntry(file);
                    zipEntry(zos, entry, sourceFolder, file);
                }

                zos.closeEntry();
            }
        }
    }

    private static void zipEntry(ZipOutputStream zos, ZipEntry entry, String sourceFolder, String file) throws IOException {
        zos.putNextEntry(entry);

        byte[] buffer = new byte[1024];
        try(FileInputStream in = new FileInputStream(sourceFolder + File.separator + file)) {
            int len;
            while ((len = in .read(buffer)) > 0)
                zos.write(buffer, 0, len);
        }
    }

    private static Collection<String> getFilesTree(String source) {
        Collection<String> tree = new LinkedList<>();

        parseFilesTree(source, new File(source), tree);

        return tree;
    }

    private static void parseFilesTree(String source, File node, Collection<String> collector) {
        if (node.isFile())
            collector.add(generateZipEntry(source, node.toString()));

        if (node.isDirectory()){
            String[] subNodes = node.list();

            if(subNodes == null)
                return;

            for(String subNode : subNodes)
                parseFilesTree(source, new File(node, subNode), collector);
        }
    }

    private static String generateZipEntry(String source, String file) {
        return file.substring(source.length() + 1);
    }


    public static void unzip(String filePath, String destPath) throws IOException {
        try(ZipInputStream zis = new ZipInputStream(new FileInputStream(filePath))) {
            ZipEntry zipEntry = zis.getNextEntry();

            while (zipEntry != null) {
                unzipEntry(new File(destPath), zis, zipEntry);
                zipEntry = zis.getNextEntry();
            }

            zis.closeEntry();
        }
    }

    private static void unzipEntry(File destinationDir, ZipInputStream zis, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        if(zipEntry.isDirectory())
            destFile.mkdirs();
        else
            destFile.getParentFile().mkdirs();

        byte[] buffer = new byte[1024];
        try(FileOutputStream fos = new FileOutputStream(destFile)) {
            int len;
            while ((len = zis.read(buffer)) > 0)
                fos.write(buffer, 0, len);
        }
    }

}
