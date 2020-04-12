package com.yc.common.utils;

import com.yc.common.global.error.Error;
import com.yc.common.global.error.ErrorException;
import com.yc.common.propertie.UploadProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-04-07
 * @Version: 1.0.0
 */
@Component
public class DlFileUtil extends org.apache.commons.io.FileUtils{

    public static final long GB = 1073741824;
    public static final long MB = 1048576;
    public static final long KB = 1024;

    public static final String MTV_REG = "^.*\\.(mp4|3gp)$";
    public static final String MP3_REG = "^.*\\.(mp3|wav)$";
    public static final String JPG_REG = "^.*\\.(gif|jpg|png)$";

    private static final String FILENAME_REGIX = "^[^\\/?\"*:<>\\]{1,255}$";

    private static UploadProperty uploadProperty;

    @Autowired
    public void setHaitaoUploadProperty(UploadProperty uploadProperty) {
        DlFileUtil.uploadProperty = uploadProperty;
    }

    /**
     * 获取临时目录
     *
     * @param fileName 文件名，如果null则返回临时目录根路径
     * @return
     */
    public static String getTempPath(String fileName) {
        String path = DlFileUtil.getTempDirectoryPath() + uploadProperty.getTempProjectName();
        File tempFile = new File(path);
        if (!tempFile.exists()) {
            boolean success = tempFile.mkdir();
            if (!success) {
                throw new ErrorException(Error.ServiceError);
            }
        }
        if (StringUtils.isBlank(fileName)) {
            return path;
        } else {
            return path + File.separator + fileName;
        }
    }

    /**
     * 获取临时目录
     *
     * @param fileName 文件名，如果null则返回临时目录根路径
     * @return
     */
    public static File getTempFile(String fileName) {
        String path = getTempPath(fileName);
        return new File(path);
    }

    /**
     * 删除文件或者空的文件夹
     *
     * @param file File对象
     * @return 执行结果
     */
    public static boolean deleteFile(File file) {
        return file.delete();
    }

    /**
     * 递归删除文件和文件夹
     *
     * @param file 要删除的根目录
     * @return {@code true} if this file was deleted, {@code false} otherwise.
     */
    public static boolean DeleteFile(File file) {
        if (file == null || !file.exists()) {
            return false;
        }
        if (file.isFile()) {
            return file.delete();
        }
        if (!file.isDirectory()) {
            return false;
        }
        File[] childFile = file.listFiles();
        if (childFile == null || childFile.length == 0) {
            return file.delete();
        }
        for (File f : childFile) {
            DeleteFile(f);
        }
        return file.delete();
    }

    /**
     * 重命名文件和文件夹
     *
     * @param file        File对象
     * @param newFileName 新的文件名
     * @return 执行结果
     */
    public static boolean renameFile(File file, String newFileName) {
        if (newFileName.matches(FILENAME_REGIX)) {
            File newFile = null;
            if (file.isDirectory()) {
                newFile = new File(file.getParentFile(), newFileName);
            } else {
                String temp = newFileName
                        + file.getName().substring(
                        file.getName().lastIndexOf('.'));
                newFile = new File(file.getParentFile(), temp);
            }
            if (file.renameTo(newFile)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 文件大小获取
     *
     * @param file File对象
     * @return 文件大小字符串
     */
    public static String getFileSize(File file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            int length = fis.available();
            if (length >= GB) {
                return String.format("%.2f GB", length * 1.0 / GB);
            } else if (length >= MB) {
                return String.format("%.2f MB", length * 1.0 / MB);
            } else {
                return String.format("%.2f KB", length * 1.0 / KB);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "未知";
    }

    /**
     * 用于递归查找文件夹下面的符合条件的文件
     *
     * @param folder 文件夹
     * @param filter 文件过滤器
     * @return 符合条件的文件List
     */
    public static List<HashMap<String, Object>> recursionFolder(File folder,
                                                                FileFilter filter) {
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

        // 获得文件夹下的所有目录和文件集合
        File[] files = folder.listFiles();
        /** 如果文件夹下没内容,会返回一个null **/
        // 判断适配器是否为空
        if (filter != null) {
            files = folder.listFiles(filter);
        }
        // 找到合适的文件返回
        if (files != null) {
            for (int m = 0; m < files.length; m++) {
                File file = files[m];
                if (file.isDirectory()) {
                    // 是否递归调用
                    list.addAll(recursionFolder(file, filter));

                } else {
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("file", file);
                    // 设置图标种类
                    if (file.getAbsolutePath().toLowerCase().matches(MP3_REG)) {
                        map.put("iconType", 3);
                    } else if (file.getAbsolutePath().toLowerCase()
                            .matches(MTV_REG)) {
                        map.put("iconType", 4);
                    } else if (file.getAbsolutePath().toLowerCase()
                            .matches(JPG_REG)) {
                        map.put("iconType", 5);
                    } else {
                        map.put("iconType", 6);
                    }
                    list.add(map);
                }
            }
        }
        return list;
    }

    /**
     * 示例:"^.*\\.(mp3|mp4|3gp)$"
     *
     * @param reg 目前允许取值 REG_MTV, REG_MP3, REG_JPG三种
     * @return 文件过滤器
     */
    public static FileFilter getFileFilter(final String reg, boolean isdir) {
        if (isdir) {
            return new FileFilter() {
                @Override
                public boolean accept(File pathname) {

                    return pathname.getAbsolutePath().toLowerCase()
                            .matches(reg)
                            || pathname.isDirectory();
                }
            };
        } else {
            return new FileFilter() {
                @Override
                public boolean accept(File pathname) {

                    return pathname.getAbsolutePath().toLowerCase()
                            .matches(reg)
                            && pathname.isFile();
                }
            };
        }
    }

    public final static String FILE_EXTENSION_SEPARATOR = ".";

    /**
     * read file
     *
     * @param filePath    文件路径
     * @param charsetName The name of a supported {@link java.nio.charset.Charset
     *                    </code>charset<code>}
     * @return if file not exist, return null, else return content of file
     * @throws RuntimeException if an error occurs while operator BufferedReader
     */
    public static StringBuilder readFile(String filePath, String charsetName) {
        File file = new File(filePath);
        StringBuilder fileContent = new StringBuilder("");
        if (!file.isFile()) {
            return null;
        }

        BufferedReader reader = null;
        try {
            InputStreamReader is = new InputStreamReader(new FileInputStream(
                    file), charsetName);
            reader = new BufferedReader(is);
            String line;
            while ((line = reader.readLine()) != null) {
                if (!fileContent.toString().equals("")) {
                    fileContent.append("\r\n");
                }
                fileContent.append(line);
            }
            reader.close();
            return fileContent;
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }

    /**
     * write file
     *
     * @param filePath 文件路径
     * @param content  内容
     * @param append   is append, if true, write to the end of file, else clear
     *                 content of file and write into it
     * @return return false if content is empty, true otherwise
     * @throws RuntimeException if an error occurs while operator FileWriter
     */
    public static boolean writeFile(String filePath, String content,
                                    boolean append) {
        if (StringUtils.isEmpty(content)) {
            return false;
        }

        FileWriter fileWriter = null;
        try {
            makeDirs(filePath);
            fileWriter = new FileWriter(filePath, append);
            fileWriter.write(content);
            fileWriter.close();
            return true;
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }

    /**
     * write file
     *
     * @param filePath    文件路径
     * @param contentList 内容List
     * @param append      is append, if true, write to the end of file, else clear
     *                    content of file and write into it
     * @return return false if contentList is empty, true otherwise
     * @throws RuntimeException if an error occurs while operator FileWriter
     */
    public static boolean writeFile(String filePath, List<String> contentList,
                                    boolean append) {
        if (contentList == null || contentList.size() < 1) {
            return false;
        }

        FileWriter fileWriter = null;
        try {
            makeDirs(filePath);
            fileWriter = new FileWriter(filePath, append);
            int i = 0;
            for (String line : contentList) {
                if (i++ > 0) {
                    fileWriter.write("\r\n");
                }
                fileWriter.write(line);
            }
            fileWriter.close();
            return true;
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }

    /**
     * write file, the string will be written to the begin of the file
     *
     * @param filePath 文件路径
     * @param content  内容
     * @return 执行结果
     */
    public static boolean writeFile(String filePath, String content) {
        return writeFile(filePath, content, false);
    }

    /**
     * write file, the string list will be written to the begin of the file
     *
     * @param filePath    文件路径
     * @param contentList 内容List
     * @return 执行结果
     */
    public static boolean writeFile(String filePath, List<String> contentList) {
        return writeFile(filePath, contentList, false);
    }

    /**
     * write file, the bytes will be written to the begin of the file
     *
     * @param filePath 文件路径
     * @param stream   InputStream
     * @return 执行结果
     * @see {@link #writeFile(String, InputStream, boolean)}
     */
    public static boolean writeFile(String filePath, InputStream stream) {
        return writeFile(filePath, stream, false);
    }

    /**
     * write file
     *
     * @param filePath the file to be opened for writing.
     * @param stream   the input stream
     * @param append   if <code>true</code>, then bytes will be written to the end of
     *                 the file rather than the beginning
     * @return return true
     * @throws RuntimeException if an error occurs while operator FileOutputStream
     */
    public static boolean writeFile(String filePath, InputStream stream,
                                    boolean append) {
        return writeFile(filePath != null ? new File(filePath) : null, stream,
                append);
    }

    /**
     * write file, the bytes will be written to the begin of the file
     *
     * @param file   File对象
     * @param stream InputStream
     * @return
     * @see {@link #writeFile(File, InputStream, boolean)}
     */
    public static boolean writeFile(File file, InputStream stream) {
        return writeFile(file, stream, false);
    }

    /**
     * write file
     *
     * @param file   the file to be opened for writing.
     * @param stream the input stream
     * @param append if <code>true</code>, then bytes will be written to the end of
     *               the file rather than the beginning
     * @return return true
     * @throws RuntimeException if an error occurs while operator FileOutputStream
     */
    public static boolean writeFile(File file, InputStream stream,
                                    boolean append) {
        OutputStream o = null;
        try {
            makeDirs(file.getAbsolutePath());
            o = new FileOutputStream(file, append);
            byte data[] = new byte[1024];
            int length = -1;
            while ((length = stream.read(data)) != -1) {
                o.write(data, 0, length);
            }
            o.flush();
            return true;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException occurred. ", e);
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (o != null) {
                try {
                    o.close();
                    stream.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }

    /**
     * copy file
     *
     * @param sourceFilePath 源文件路径
     * @param destFilePath   目标文件路径
     * @return 执行结果
     * @throws RuntimeException if an error occurs while operator FileOutputStream
     */
    public static boolean copyFile(String sourceFilePath, String destFilePath) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(sourceFilePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException occurred. ", e);
        }
        return writeFile(destFilePath, inputStream);
    }

    /**
     * 输入流转byte[]
     *
     * @param inStream InputStream
     * @return Byte数组
     */
    public static final byte[] input2byte(InputStream inStream) {
        if (inStream == null)
            return null;
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        try {
            while ((rc = inStream.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return swapStream.toByteArray();
    }

    /**
     * read file to string list, a element of list is a line
     *
     * @param filePath    文件路径
     * @param charsetName 编码方式
     *                    The name of a supported {@link java.nio.charset.Charset
     *                    </code>charset<code>}
     * @return if file not exist, return null, else return content of file
     * @throws RuntimeException if an error occurs while operator BufferedReader
     */
    public static List<String> readFileToList(String filePath,
                                              String charsetName) {
        File file = new File(filePath);
        List<String> fileContent = new ArrayList<String>();
        if (!file.isFile()) {
            return null;
        }

        BufferedReader reader = null;
        try {
            InputStreamReader is = new InputStreamReader(new FileInputStream(
                    file), charsetName);
            reader = new BufferedReader(is);
            String line = null;
            while ((line = reader.readLine()) != null) {
                fileContent.add(line);
            }
            reader.close();
            return fileContent;
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }

    /**
     * get file name from path, not include suffix
     * <p/>
     * <pre>
     *      getFileNameWithoutExtension(null)               =   null
     *      getFileNameWithoutExtension("")                 =   ""
     *      getFileNameWithoutExtension("   ")              =   "   "
     *      getFileNameWithoutExtension("abc")              =   "abc"
     *      getFileNameWithoutExtension("a.mp3")            =   "a"
     *      getFileNameWithoutExtension("a.b.rmvb")         =   "a.b"
     *      getFileNameWithoutExtension("c:\\")              =   ""
     *      getFileNameWithoutExtension("c:\\a")             =   "a"
     *      getFileNameWithoutExtension("c:\\a.b")           =   "a"
     *      getFileNameWithoutExtension("c:a.txt\\a")        =   "a"
     *      getFileNameWithoutExtension("/home/admin")      =   "admin"
     *      getFileNameWithoutExtension("/home/admin/a.txt/b.mp3")  =   "b"
     * </pre>
     *
     * @param filePath 文件路径
     * @return file name from path, not include suffix
     * @see
     */
    public static String getFileNameWithoutExtension(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return filePath;
        }

        int extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        int filePosi = filePath.lastIndexOf(File.separator);
        if (filePosi == -1) {
            return (extenPosi == -1 ? filePath : filePath.substring(0,
                    extenPosi));
        }
        if (extenPosi == -1) {
            return filePath.substring(filePosi + 1);
        }
        return (filePosi < extenPosi ? filePath.substring(filePosi + 1,
                extenPosi) : filePath.substring(filePosi + 1));
    }

    /**
     * get file name from path, include suffix
     * <p/>
     * <pre>
     *      getFileName(null)               =   null
     *      getFileName("")                 =   ""
     *      getFileName("   ")              =   "   "
     *      getFileName("a.mp3")            =   "a.mp3"
     *      getFileName("a.b.rmvb")         =   "a.b.rmvb"
     *      getFileName("abc")              =   "abc"
     *      getFileName("c:\\")              =   ""
     *      getFileName("c:\\a")             =   "a"
     *      getFileName("c:\\a.b")           =   "a.b"
     *      getFileName("c:a.txt\\a")        =   "a"
     *      getFileName("/home/admin")      =   "admin"
     *      getFileName("/home/admin/a.txt/b.mp3")  =   "b.mp3"
     * </pre>
     *
     * @param filePath 文件路径
     * @return file name from path, include suffix
     */
    public static String getFileName(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return filePath;
        }

        int filePosi = filePath.lastIndexOf(File.separator);
        return (filePosi == -1) ? filePath : filePath.substring(filePosi + 1);
    }

    /**
     * get folder name from path
     * <p/>
     * <pre>
     *      getFolderName(null)               =   null
     *      getFolderName("")                 =   ""
     *      getFolderName("   ")              =   ""
     *      getFolderName("a.mp3")            =   ""
     *      getFolderName("a.b.rmvb")         =   ""
     *      getFolderName("abc")              =   ""
     *      getFolderName("c:\\")              =   "c:"
     *      getFolderName("c:\\a")             =   "c:"
     *      getFolderName("c:\\a.b")           =   "c:"
     *      getFolderName("c:a.txt\\a")        =   "c:a.txt"
     *      getFolderName("c:a\\b\\c\\d.txt")    =   "c:a\\b\\c"
     *      getFolderName("/home/admin")      =   "/home"
     *      getFolderName("/home/admin/a.txt/b.mp3")  =   "/home/admin/a.txt"
     * </pre>
     *
     * @param filePath 文件路径
     * @return 文件夹名称
     */
    public static String getFolderName(String filePath) {

        if (StringUtils.isEmpty(filePath)) {
            return filePath;
        }

        int filePosi = filePath.lastIndexOf(File.separator);
        return (filePosi == -1) ? "" : filePath.substring(0, filePosi);
    }

    /**
     * get suffix of file from path
     * <p/>
     * <pre>
     *      getFileExtension(null)               =   ""
     *      getFileExtension("")                 =   ""
     *      getFileExtension("   ")              =   "   "
     *      getFileExtension("a.mp3")            =   "mp3"
     *      getFileExtension("a.b.rmvb")         =   "rmvb"
     *      getFileExtension("abc")              =   ""
     *      getFileExtension("c:\\")              =   ""
     *      getFileExtension("c:\\a")             =   ""
     *      getFileExtension("c:\\a.b")           =   "b"
     *      getFileExtension("c:a.txt\\a")        =   ""
     *      getFileExtension("/home/admin")      =   ""
     *      getFileExtension("/home/admin/a.txt/b")  =   ""
     *      getFileExtension("/home/admin/a.txt/b.mp3")  =   "mp3"
     * </pre>
     *
     * @param filePath 文件路径
     * @return
     */
    public static String getFileExtension(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return filePath;
        }

        int extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        int filePosi = filePath.lastIndexOf(File.separator);
        if (extenPosi == -1) {
            return "";
        }
        return (filePosi >= extenPosi) ? "" : filePath.substring(extenPosi + 1);
    }

    /**
     * Creates the directory named by the trailing filename of this file,
     * including the complete directory path required to create this directory. <br/>
     * <br/>
     * <ul>
     * <strong>Attentions:</strong>
     * <li>makeDirs("C:\\Users\\Trinea") can only create users folder</li>
     * <li>makeFolder("C:\\Users\\Trinea\\") can create Trinea folder</li>
     * </ul>
     *
     * @param filePath 文件路径
     * @return true if the necessary directories have been created or the target
     * directory already exists, false one of the directories can not be
     * created.
     * <ul>
     * <li>if {@link DlFileUtil#getFolderName(String)} return null,
     * return false</li>
     * <li>if target directory already exists, return true</li>
     * <li>return {@link File}</li>
     * </ul>
     */
    public static boolean makeDirs(String filePath) {
        String folderName = getFolderName(filePath);
        if (StringUtils.isEmpty(folderName)) {
            return false;
        }

        File folder = new File(folderName);
        return (folder.exists() && folder.isDirectory()) || folder
                .mkdirs();
    }

    /**
     * @param filePath 文件路径
     * @return 执行结果
     * @see #makeDirs(String)
     */
    public static boolean makeFolders(String filePath) {
        return makeDirs(filePath);
    }

    /**
     * Indicates if this file represents a file on the underlying file system.
     *
     * @param filePath 文件路径
     * @return 是否存在文件
     */
    public static boolean isFileExist(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return false;
        }

        File file = new File(filePath);
        return (file.exists() && file.isFile());
    }

    /**
     * Indicates if this file represents a directory on the underlying file
     * system.
     *
     * @param directoryPath 文件夹路径
     * @return 文件夹是否存在
     */
    public static boolean isFolderExist(String directoryPath) {
        if (StringUtils.isEmpty(directoryPath)) {
            return false;
        }

        File dire = new File(directoryPath);
        return (dire.exists() && dire.isDirectory());
    }

    /**
     * delete file or directory
     * <ul>
     * <li>if path is null or empty, return true</li>
     * <li>if path not exist, return true</li>
     * <li>if path exist, delete recursion. return true</li>
     * <ul>
     *
     * @param path 文件路径
     * @return 是否删除成功
     */
    public static boolean deleteFile(String path) {
        if (StringUtils.isEmpty(path)) {
            return true;
        }

        File file = new File(path);
        if (!file.exists()) {
            return true;
        }
        if (file.isFile()) {
            return file.delete();
        }
        if (!file.isDirectory()) {
            return false;
        }
        for (File f : file.listFiles()) {
            if (f.isFile()) {
                f.delete();
            } else if (f.isDirectory()) {
                deleteFile(f.getAbsolutePath());
            }
        }
        return file.delete();
    }

    /**
     * get file size
     * <ul>
     * <li>if path is null or empty, return -1</li>
     * <li>if path exist and it is a file, return file size, else return -1</li>
     * <ul>
     *
     * @param path 文件路径
     * @return returns the length of this file in bytes. returns -1 if the file
     * does not exist.
     */
    public static long getFileSize(String path) {
        if (StringUtils.isEmpty(path)) {
            return -1;
        }

        File file = new File(path);
        return (file.exists() && file.isFile() ? file.length() : -1);
    }

}
