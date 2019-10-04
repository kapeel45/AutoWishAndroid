package com.autowish.CommonUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public class CommonMethods {
    public static Bitmap bm;
    public static Matrix matrix;

    /**
     * hidesoft  keyboard
     **/
    public static void hideSoftKeyboard(Activity context) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(context.getCurrentFocus()
                    .getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void show_permission_alert(final Context context, String message) {
        final AlertDialog alertDialog = new AlertDialog.Builder(
                context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT).create();

        // Setting Dialog Title
        alertDialog.setTitle("Error!");

        // Setting Dialog Message
        alertDialog.setMessage(message);

        // Setting Icon to Dialog
//        alertDialog.setIcon(R.drawable.call);

        // Setting OK Button
        alertDialog.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                alertDialog.dismiss();
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                intent.setData(uri);
                context.startActivity(intent);
            }
        });

        alertDialog.setButton(Dialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                alertDialog.dismiss();

            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    public static void Error_Alert_Dialog(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
//        final AlertDialog alertDialog = new AlertDialog.Builder(
//                context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT).create();
//
//        // Setting Dialog Title
//        alertDialog.setTitle("Error!");
//
//        // Setting Dialog Message
//        alertDialog.setMessage(message);
//
//        // Setting Icon to Dialog
////        alertDialog.setIcon(R.drawable.call);
//
//        // Setting OK Button
//        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                // Write your code here to execute after dialog closed
//                alertDialog.dismiss();
//            }
//        });
//
//        // Showing Alert Message
//        alertDialog.show();
    }

    public static void success_Alert_Dialog(final Context context, String message) {
        final AlertDialog alertDialog = new AlertDialog.Builder(
                context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT).create();

        // Setting Dialog Title
        alertDialog.setTitle("Success!");

        // Setting Dialog Message
        alertDialog.setMessage(message);

        // Setting Icon to Dialog
//        alertDialog.setIcon(R.drawable.call);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                alertDialog.dismiss();
            }
        });

        // Showing Alert Message

        alertDialog.show();
    }

    // Method to manually check connection status
    public static boolean checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        return isConnected;
    }

    public static boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

    public static String onCaptureImageResultblur(Context con, Intent data,
                                                  Uri imageUri, boolean b, String fileName) {
        String bitmap_name = "";
        matrix = new Matrix();
        try {
            bm = MediaStore.Images.Media.getBitmap(
                    con.getContentResolver(), imageUri);
            bm = getResizedBitmap(bm, 450);
//            bm = Bitmap.createScaledBitmap(bm, bm.getWidth(), bm.getHeight(), true);
//            img_edit_profile.setImageBitmap(bm);
        } catch (IOException e) {
            e.printStackTrace();
        }
        createDirectoryAndSaveFile(bm, fileName);
        bitmap_name = Environment.getExternalStorageDirectory() + "/TagaRetailer/" + fileName.toString();
        return bitmap_name;
    }


    public static String onSelectFromGalleryResult_imageview(Context con, Intent data, boolean bool) {
        String bitmap_name = "";
        Bitmap selectedImage = null;

//
        if (data != null && !data.equals("")) {
            Bundle bundle = data.getExtras();
            Uri selectImage = data.getData();
            InputStream imageStream = null;
            try {
                imageStream = con.getContentResolver().openInputStream(selectImage);
                selectedImage = BitmapFactory.decodeStream(imageStream);
                selectedImage = getResizedBitmap(selectedImage, 450);
//                img_edit_profile.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String fileName = System.currentTimeMillis() + ".png";
            createDirectoryAndSaveFile(selectedImage, fileName);
            bitmap_name = Environment.getExternalStorageDirectory() + "/TagaRetailer/" + fileName.toString();

        }
        return bitmap_name;
    }

    public static void createDirectoryAndSaveFile(Bitmap imageToSave, String fileName) {

        File direct = new File(Environment.getExternalStorageDirectory() + "/TagaRetailer/");

        if (!direct.exists()) {
            File wallpaperDirectory = new File(Environment.getExternalStorageDirectory() + "/TagaRetailer/");
            wallpaperDirectory.mkdirs();
        }

        File file = new File(new File(Environment.getExternalStorageDirectory() + "/TagaRetailer/"), fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public static void show_permission_alert_img(final Context context, String message) {
        final AlertDialog alertDialog = new AlertDialog.Builder(
                context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT).create();

        // Setting Dialog Title
        alertDialog.setTitle("Error!");

        // Setting Dialog Message
        alertDialog.setMessage(message);

        // Setting Icon to Dialog
//        alertDialog.setIcon(R.drawable.call);

        // Setting OK Button
        alertDialog.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                alertDialog.dismiss();
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                intent.setData(uri);
                context.startActivity(intent);
            }
        });

        alertDialog.setButton(Dialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                alertDialog.dismiss();

            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    public static String calculateTime(long seconds) {
        String timmer = "";
        int day = (int) TimeUnit.SECONDS.toDays(seconds);
        long hours = TimeUnit.SECONDS.toHours(seconds) - (day * 24);
        long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds) * 60);
        long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) * 60);

        System.out.println("D " + day + " H " + hours + " Min " + minute + " Sec " + second);
        if (second < 10) {
            timmer = "0" + minute + " : " + "0" + second;
        } else {
            timmer = "0" + minute + " : " + second;
        }
        return timmer;
    }
}
