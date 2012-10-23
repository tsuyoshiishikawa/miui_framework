// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) nonlb 

package miui.content.res;

import android.app.MiuiThemeHelper;
import android.content.res.Resources;
import android.util.*;
import com.android.internal.util.XmlUtils;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import miui.util.DisplayUtils;
import org.xmlpull.v1.*;

// Referenced classes of package miui.content.res:
//            ThemeResources

public final class ThemeZipFile {

    public ThemeZipFile(String s, ThemeResources.MetaData metadata, String s1) {
        mLastModifyTime = -1L;
        mIntegers = new SparseArray();
        mCharSequences = new SparseArray();
        if(DBG)
            Log.d(TAG, (new StringBuilder()).append("create ThemeZipFile for ").append(s).toString());
        mPackageName = s1;
        mPath = s;
        mMetaData = metadata;
    }

    private void clean() {
        if(DBG)
            Log.d(TAG, (new StringBuilder()).append("clean for ").append(mPath).toString());
        if(mZipFile != null) {
            try {
                mZipFile.close();
            }
            catch(Exception exception) { }
            mZipFile = null;
        }
        mIntegers.clear();
        mCharSequences.clear();
    }

    private InputStream getInputStreamInner(String s, int ai[]) {
        InputStream inputstream = getZipInputStream(s, ai);
        int i = s.indexOf("dpi/");
        InputStream inputstream1;
        if(i < 0) {
            inputstream1 = inputstream;
        } else {
            String s1 = s.substring(i + 3);
            for(; s.charAt(i) != '-'; i--);
            String s2 = s.substring(0, i);
            for(int j = -1 + sDensities.length; inputstream == null && j >= 0; j--) {
                Object aobj[] = new Object[3];
                aobj[0] = s2;
                aobj[1] = DisplayUtils.getDensitySuffix(sDensities[j]);
                aobj[2] = s1;
                inputstream = getZipInputStream(String.format("%s%s%s", aobj), ai);
            }

            inputstream1 = inputstream;
        }
        return inputstream1;
    }

    private static final String getPackageName(String s) {
        if(!"framework-res".equals(s) && !"icons".equals(s)) goto _L2; else goto _L1
_L1:
        s = "android";
_L4:
        return s;
_L2:
        if("framework-miui-res".equals(s) || "lockscreen".equals(s))
            s = "miui";
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected static ThemeZipFile getThemeZipFile(ThemeResources.MetaData metadata, String s) {
        String s1 = (new StringBuilder()).append(metadata.themePath).append(s).toString();
        if(!sThemeZipFiles.containsKey(s1))
            synchronized(sThemeZipFiles) {
                if(!sThemeZipFiles.containsKey(s1))
                    sThemeZipFiles.put(s1, new ThemeZipFile(s1, metadata, getPackageName(s)));
            }
        return (ThemeZipFile)sThemeZipFiles.get(s1);
        exception;
        map;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private InputStream getZipInputStream(String s, int ai[]) {
        InputStream inputstream = null;
        if(isValid()) goto _L2; else goto _L1
_L1:
        return inputstream;
_L2:
        InputStream inputstream1;
        ZipEntry zipentry = mZipFile.getEntry(s);
        if(zipentry == null)
            continue; /* Loop/switch isn't completed */
        if(ai != null && ai.length > 0)
            ai[0] = (int)zipentry.getSize();
        inputstream1 = mZipFile.getInputStream(zipentry);
        inputstream = inputstream1;
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        if(true) goto _L1; else goto _L3
_L3:
    }

    private boolean isValid() {
        boolean flag;
        if(mZipFile != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void loadThemeValues(Resources resources) {
        int i;
        if(DBG)
            Log.d(TAG, (new StringBuilder()).append("loadThemeValues for ").append(mPath).toString());
        i = 0;
_L2:
        Object obj;
        if(i >= sDensities.length)
            break; /* Loop/switch isn't completed */
        Object aobj[] = new Object[1];
        aobj[0] = DisplayUtils.getDensitySuffix(sDensities[i]);
        obj = getZipInputStream(String.format("theme_values%s.xml", aobj), null);
        if(obj == null)
            break MISSING_BLOCK_LABEL_123;
        XmlPullParser xmlpullparser;
        BufferedInputStream bufferedinputstream;
        xmlpullparser = XmlPullParserFactory.newInstance().newPullParser();
        bufferedinputstream = new BufferedInputStream(((InputStream) (obj)), 8192);
        xmlpullparser.setInput(bufferedinputstream, null);
        mergeThemeValues(resources, xmlpullparser);
        Exception exception;
        try {
            bufferedinputstream.close();
        }
        catch(IOException ioexception2) {
            ioexception2.printStackTrace();
        }
        i++;
        if(true) goto _L2; else goto _L1
        exception;
_L3:
        XmlPullParserException xmlpullparserexception;
        IOException ioexception;
        try {
            ((InputStream) (obj)).close();
        }
        catch(IOException ioexception1) {
            ioexception1.printStackTrace();
        }
        throw exception;
_L1:
        return;
        xmlpullparserexception;
_L4:
        try {
            ((InputStream) (obj)).close();
        }
        // Misplaced declaration of an exception variable
        catch(IOException ioexception) {
            ioexception.printStackTrace();
        }
        break MISSING_BLOCK_LABEL_123;
        exception;
        obj = bufferedinputstream;
          goto _L3
        XmlPullParserException xmlpullparserexception1;
        xmlpullparserexception1;
        obj = bufferedinputstream;
          goto _L4
    }

    private void mergeThemeValues(Resources resources, XmlPullParser xmlpullparser) {
        int i;
        while(i != 2 && i != 1) 
            i = xmlpullparser.next();
        if(i != 2)
            throw new XmlPullParserException("No start tag found");
          goto _L1
        Exception exception;
        exception;
        exception.printStackTrace();
_L3:
        return;
_L1:
        int j;
        do
            j = xmlpullparser.next();
        while(j != 2 && j != 1);
        if(j == 1) goto _L3; else goto _L2
_L2:
        String s = xmlpullparser.getName().trim();
        if(s == null) goto _L3; else goto _L4
_L4:
        String s1;
        String s2;
        int k;
        s1 = null;
        s2 = null;
        k = -1 + xmlpullparser.getAttributeCount();
_L13:
        if(k < 0) goto _L6; else goto _L5
_L5:
        String s4 = xmlpullparser.getAttributeName(k).trim();
        if(s4.equals("name"))
            s1 = xmlpullparser.getAttributeValue(k);
        else
        if(s4.equals("package"))
            s2 = xmlpullparser.getAttributeValue(k);
          goto _L7
_L6:
        String s3 = xmlpullparser.nextText();
        if(s1 == null || s3 == null || s3.length() == 0) goto _L1; else goto _L8
_L8:
        int l;
        if(s2 == null)
            s2 = mPackageName;
        l = resources.getIdentifier(s1, s, s2);
        if(l <= 0) goto _L1; else goto _L9
_L9:
        if(!s.equals("bool")) goto _L11; else goto _L10
_L10:
        if(!mMetaData.supportInt || mIntegers.indexOfKey(l) >= 0) goto _L1; else goto _L12
_L12:
        SparseArray sparsearray;
        int i1;
        sparsearray = mIntegers;
        if(!"true".equals(s3.trim()))
            break MISSING_BLOCK_LABEL_478;
        i1 = 1;
_L14:
        sparsearray.put(l, Integer.valueOf(i1));
          goto _L1
_L11:
        if(s.equals("color") || s.equals("integer") || s.equals("drawable")) {
            if(mMetaData.supportInt && mIntegers.indexOfKey(l) < 0)
                mIntegers.put(l, Integer.valueOf(XmlUtils.convertValueToUnsignedInt(s3.trim(), 0)));
        } else
        if(s.equals("string")) {
            if(mMetaData.supportCharSequence && mCharSequences.indexOfKey(l) < 0)
                mCharSequences.put(l, s3);
        } else
        if(s.equals("dimen") && mMetaData.supportInt && mIntegers.indexOfKey(l) < 0) {
            Integer integer = MiuiThemeHelper.parseDimension(s3.toString());
            if(integer != null)
                mIntegers.put(l, integer);
        }
          goto _L1
_L7:
        k--;
          goto _L13
        i1 = 0;
          goto _L14
    }

    private void openZipFile() {
        if(DBG)
            Log.d(TAG, (new StringBuilder()).append("openZipFile for ").append(mPath).toString());
        File file = new File(mPath);
        mLastModifyTime = file.lastModified();
        if(mLastModifyTime != 0L)
            try {
                mZipFile = new ZipFile(file);
            }
            catch(Exception exception) {
                mZipFile = null;
            }
    }

    public boolean checkUpdate(Resources resources) {
        long l;
        if(DBG)
            Log.d(TAG, (new StringBuilder()).append("checkUpdate for ").append(mPath).toString());
        l = (new File(mPath)).lastModified();
        if(mLastModifyTime == l) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorenter ;
        if(mLastModifyTime == l) goto _L2; else goto _L3
_L3:
        boolean flag;
        clean();
        openZipFile();
        loadThemeValues(resources);
        flag = true;
_L5:
        return flag;
_L2:
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public boolean containsEntry(String s) {
        boolean flag;
        if(isValid() && mZipFile.getEntry(s) != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean exists() {
        return (new File(mPath)).exists();
    }

    public InputStream getInputStream(String s, int ai[]) {
        InputStream inputstream;
        if(mMetaData.supportFile)
            inputstream = getInputStreamInner(s, ai);
        else
            inputstream = null;
        return inputstream;
    }

    public CharSequence getThemeCharSequence(int i) {
        return (CharSequence)mCharSequences.get(i);
    }

    public Integer getThemeInt(int i) {
        return (Integer)mIntegers.get(i);
    }

    public boolean hasValues() {
        boolean flag;
        if(mIntegers.size() > 0 || mCharSequences.size() > 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static final String ALTERNATIVE_THEME_VALUE_FILE = "theme_values%s.xml";
    private static final String ATTR_NAME = "name";
    private static final String ATTR_PACKAGE = "package";
    static boolean DBG = false;
    static String TAG = "ThemeZipFile";
    private static final String TAG_BOOLEAN = "bool";
    private static final String TAG_COLOR = "color";
    private static final String TAG_DIMEN = "dimen";
    private static final String TAG_DRAWABLE = "drawable";
    private static final String TAG_INTEGER = "integer";
    private static final String TAG_ROOT = "MIUI_Theme_Values";
    private static final String TAG_STRING = "string";
    public static final String THEME_VALUE_FILE = "theme_values.xml";
    private static final String TRUE = "true";
    private static final int sDensities[];
    private static final int sDensity;
    protected static final Map sThemeZipFiles = new HashMap();
    private SparseArray mCharSequences;
    private SparseArray mIntegers;
    private long mLastModifyTime;
    private ThemeResources.MetaData mMetaData;
    private String mPackageName;
    private String mPath;
    private ZipFile mZipFile;

    static  {
        DBG = ThemeResources.DBG;
        sDensity = DisplayMetrics.DENSITY_DEVICE;
        sDensities = DisplayUtils.getBestDensityOrder(sDensity);
    }
}