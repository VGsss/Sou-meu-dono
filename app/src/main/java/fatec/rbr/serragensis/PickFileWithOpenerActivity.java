/**
 * Copyright 2013 Google Inc. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fatec.rbr.serragensis;

import android.content.Intent;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.OpenFileActivityBuilder;

public class PickFileWithOpenerActivity extends MainActivity {

    private final String TAG = "PickFile";

    private static final int REQUEST_CODE_OPENER = 1;

    @Override
    public void onConnected(Bundle connectionHint) {
        super.onConnected(connectionHint);
        IntentSender intentSender = Drive.DriveApi
                .newOpenFileActivityBuilder()
                .setMimeType(new String[] {"application/x-sqlite3"})
                .build(getGoogleApiClient());
        try {
            startIntentSenderForResult(
                    intentSender, REQUEST_CODE_OPENER, null, 0, 0, 0);
        } catch (SendIntentException e) {
            Log.e(TAG,"Error opening the file");
            showMessage("Erro em Abrir arquivo");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_OPENER:
                if (resultCode == RESULT_OK) {
                    DriveId driveId = data.getParcelableExtra(OpenFileActivityBuilder.EXTRA_RESPONSE_DRIVE_ID);
<<<<<<< HEAD:app/src/main/java/fatec/rbr/serragensis/PickFileWithOpenerActivity.java
                    Intent myIntent = new Intent(getBaseContext(), SetBD.class);
                    myIntent.putExtra("fileID", driveId.getResourceId());
                    Log.i(TAG,"No problems, will open the Text Editor");
                    startActivity(myIntent);
                }
                else{
                    showMessage("Erro desconhecido");
                    Log.e(TAG,"Deu algum erro desconhecido" + resultCode);
=======
                    Intent myIntent = new Intent(getBaseContext(), TextEditor.class);
                    myIntent.putExtra("fileID", driveId.getResourceId());
                    Log.i(TAG,"No problems, will open the Text Editor");
                    startActivity(myIntent);
>>>>>>> origin/master:app/src/main/java/fatec/rbr/serragensis/PickFileWithOpenerActivity.java
                }
                else{
                    showMessage("deu merda");
                    Log.e(TAG,"" + resultCode);
                }

                finish();
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }
}

