package Model;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class MyFileHandler implements Iterable<Product> {
    private final File file;
    private RandomAccessFile raf;
    public static final String F_NAME = "products.txt";

    public void resetRandomAccessFile(){
        try{
            raf=new RandomAccessFile(file,"rw");
        } catch (FileNotFoundException e) {
        }
    }

    public MyFileHandler() {
        file=new File(F_NAME);
        resetRandomAccessFile();
    }

    @Override
    public Iterator<Product> iterator() {
        return new myFileIterator();
    }

    public void addProd(Product p) throws IOException {
        removeProductByCode(p.getCode());

            raf.seek(raf.length());
            byte[] data = convertToBytes(p);
            raf.writeInt(data.length);
            raf.write(data);

    }

    public void addAll(Map<String,Product> map) throws IOException {
        for (Map.Entry<String, Product> entry : map.entrySet()){
            addProd(entry.getValue());
        }
    }

    public boolean removeProductByCode(String code) throws IOException {
        Iterator<Product> it=iterator();
        long pos;
        while(it.hasNext()){
            pos=raf.getFilePointer();
            Product p=it.next();
            if(p.getCode().equals(code)){
                byte[] data = new byte[(int) (raf.length() - raf.getFilePointer())];
                raf.read(data);
                raf.setLength(pos);
                raf.write(data);
                return true;
            }
        }
        return false;
    }

    public void removeAll() throws IOException {
        Iterator<Product> it=iterator();
        while(it.hasNext()){
            removeProductByCode(it.next().getCode());
            raf.seek(0);
        }
    }

    public byte[] nextProductToByteArr() throws IOException {
        int len=raf.readInt();
        byte[] data=new byte[len];
        raf.read(data);
        return data;
    }

    private byte[] convertToBytes(Product p) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(p);
            return bos.toByteArray();
        }
    }

    public Object bytesToProduct(byte[] data) throws IOException, ClassNotFoundException {
        try(
                ByteArrayInputStream bin=new ByteArrayInputStream(data);
                ObjectInputStream ois=new ObjectInputStream(bin)
        ) {
            return ois.readObject();
        }
    }

    public LinkedHashMap<String,Product> fileToMap(){
        LinkedHashMap<String,Product> m=new LinkedHashMap<String, Product>();
        Iterator<Product> it=iterator();
        while(it.hasNext()){
            Product p=it.next();
            m.put(p.getCode(),p);
        }
        return m;
    }

    class myFileIterator implements Iterator<Product>{
        public myFileIterator() {
            resetRandomAccessFile();
        }

        @Override
        public boolean hasNext() {
            try {
                return raf.getFilePointer()<raf.length();
            } catch (IOException e) {
                return false;
            }
        }

        @Override
        public Product next() {
            try {
                byte[] data= nextProductToByteArr();
                return (Product) bytesToProduct(data);
            } catch (IOException | ClassNotFoundException e) {
                return null;
            }
        }
    }
}
