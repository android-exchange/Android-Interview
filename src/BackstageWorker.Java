
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BackstageWorker {
    public static void main(String[] args) {
        newcomerRegistration(null, null, 0);
        directoryUpdate(null,null,0);
    }

    private static int newcomerRegistration(ArrayList<File> arrayList, File file, int sequence) {
        if (file != null && !file.isDirectory() && -1 != getIndex(file)) {
            Integer integer = getIndex(file);
            if (integer > sequence) {
                sequence = integer;
            }
        }
        if (null == file) {
            File sourceFile = getSourceFile();
            if (sourceFile == null || !sourceFile.isDirectory()) {
                throw new NullPointerException("Source file cannot be empty");
            }
            ArrayList<File> fileArrayList = new ArrayList<>();
            for (File f : sourceFile.listFiles()) {
                sequence = newcomerRegistration(fileArrayList, f, sequence);
            }
            File f = null;
            String format = null;
            Scanner scanner = new Scanner(System.in);
            for (int i = fileArrayList.size()-1; i >= 0; i--) {
                f = fileArrayList.get(i);
                String name = f.getName();
                if (name.contains("-") && name.indexOf("-") == 0) {
                    name = name.replace("-", "");
                }
                format = String.format("-%d-%s", sequence++, name);
                System.out.println(name + " rename " + format + "  y/n");
                String anObject = "";
                while (true) {
                    anObject = scanner.nextLine();
                    if ("y".equals(anObject) || "n".equals(anObject)) {
                        break;
                    }
                    System.out.println("Unable to recognize input");
                }
                if ("y".equals(anObject)) {
                    System.out.println(f.renameTo(new File(f.getParent(), format)));
                    fileArrayList.remove(i);
                }
            }
            System.out.println("Name format is incorrect:");
            for (int j = 0; j < fileArrayList.size(); j++) {
                System.out.println(fileArrayList.get(j).getPath());
            }
        } else if (!file.isDirectory() && -1 == getIndex(file)) {
            arrayList.add(file);
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    sequence = newcomerRegistration(arrayList, f, sequence);
                }
            }
        }
        return sequence;
    }

    public static void directoryUpdate(File tagerFile,StringBuilder stringBuilder,int floor) {
        if (tagerFile == null) {
            File file = Paths.get(System.getProperty("user.dir"), "Directory.md").toFile();
            if (!file.exists()) {
                try {
                    boolean newFile = file.createNewFile();
                    System.out.println("newFile:" + newFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            File sourceFile = getSourceFile();
            File[] files = sourceFile.listFiles();
            if (files != null){
                stringBuilder = new StringBuilder();
                floor++;
                for (File f : files) {
                    directoryUpdate(f,stringBuilder,floor);
                }
            }
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                bw.write(stringBuilder.toString());
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (!tagerFile.isDirectory()){
//            for (int i = 0; i < floor; i++) {
//                stringBuilder.append("#");
//            }
            stringBuilder
                    .append("##### [")
                    .append(tagerFile.getName())
                    .append("](")
                    .append(getDomainNameConversion(tagerFile))
                    .append(")\n");
        }else if (tagerFile.isDirectory()){
            for (int i = 0; i < floor; i++) {
                stringBuilder.append("#");
            }
            stringBuilder
                    .append(" [")
                    .append(tagerFile.getName())
                    .append("](")
                    .append(getDomainNameConversion(tagerFile))
                    .append(")\n");
            File[] files = tagerFile.listFiles();
            if (files != null){
                floor++;
                for (File f : files) {
                    directoryUpdate(f,stringBuilder,floor);
                }
            }
        }
    }

    private static int getIndex(File file) {
        int index = -1;
        String name = file.getName();
        int count = 0;
        Pattern p = Pattern.compile("-");
        Matcher m = p.matcher(name);
        while (m.find()) {
            count++;
        }
        if (count < 2) {
            return index;
        }
        String substring = name.substring(name.indexOf("-") + 1, name.indexOf("-", name.indexOf("-") + 1));
        if ("".equals(substring)) {
            return index;
        }
        index = Integer.valueOf(substring);
        return index;
    }

    /**
     * get source file
     *
     * @return
     */
    private static File getSourceFile() {
        return Paths.get(System.getProperty("user.dir"), "resources", "sourcefile").toFile();
    }

    private static String getDomainNameConversion(File file){
        String domainName = "https://github.com/android-exchange/Android-Interview/tree/master";

        Path path = Paths.get(file.getPath().replace(System.getProperty("user.dir"),""));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(domainName);
        Iterator<Path> iterator = path.iterator();
        while (iterator.hasNext()){
            stringBuilder.append("/").append(iterator.next());
        }
        return stringBuilder.toString();
    }
}