package com.project.revolvingcabinet.utils;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
public class PasswordUtil {


    public static String decrypt(String origin) {
        try {
            ComThread.InitSTA();
            ActiveXComponent com = new ActiveXComponent("EncryptUtil.SymmetrySecret");
            Dispatch dispatch = com.getObject();
            Variant call = Dispatch.call(dispatch, "Decrypt", origin);
            return call.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ComThread.Release();
        }
        return null;
    }

    public static String encrypt(String origin) {
        try {
            ComThread.InitSTA();
            ActiveXComponent com = new ActiveXComponent("EncryptUtil.SymmetrySecret");
            Dispatch dispatch = com.getObject();
            Variant call = Dispatch.call(dispatch, "Encrypt", origin);
            return call.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ComThread.Release();
        }
        return null;
    }

    public void testJacob() {
        ActiveXComponent xl = new ActiveXComponent("Excel.Application");
        Dispatch xlo = xl.getObject();
        try {
            System.out.println("jacob's version=" + xl.getProperty("Version"));
            System.out.println("jacob's version=" + Dispatch.get(xlo, "Version"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            xl.invoke("Quit", new Variant[]{});
        }
    }


}
