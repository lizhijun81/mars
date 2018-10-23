package com.lizj.fd;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FdDemo {

    public static void main(String[] args) throws IOException {
        FileOutputStream out = new FileOutputStream(FileDescriptor.out);
        out.write('A');
        out.close();
    }

}