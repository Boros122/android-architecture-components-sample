package com.boros.android.starter.util

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import java.io.File

object FileUtil {

    fun createProfilePictureFile(storageDir: File): File {
        val deletedFile = File(storageDir, "profile_picture.jpg")
        if (deletedFile.exists()) {
            deletedFile.delete()
        }
        return File(storageDir, "profile_picture.jpg")
    }

    fun getMimeType(contentResolver: ContentResolver, uri: Uri): String {
        val mimeType: String?
        mimeType = if (uri.scheme == ContentResolver.SCHEME_CONTENT) {
            contentResolver.getType(uri)
        } else {
            val fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri
                    .toString())
            MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                    fileExtension.toLowerCase())
        }
        return mimeType ?: "image/png"
    }

    fun getRealPathFromURI(context: Context?, contentURI: Uri): String {
        val result: String
        val cursor = context?.contentResolver?.query(contentURI, null, null, null, null)
        if (cursor == null) {
            result = contentURI.path ?: ""
        } else {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            result = cursor.getString(idx)
            cursor.close()
        }
        return result
    }

}