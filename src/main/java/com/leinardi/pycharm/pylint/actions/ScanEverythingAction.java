/*
 * Copyright 2021 Roberto Leinardi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.leinardi.pycharm.pylint.actions;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.leinardi.pycharm.pylint.PylintPlugin;
import com.leinardi.pycharm.pylint.util.VfUtil;
import org.jetbrains.annotations.NotNull;

import java.util.List;

class ScanEverythingAction implements Runnable {

    private final Project project;

    ScanEverythingAction(@NotNull final Project project) {
        this.project = project;
    }

    @Override
    public void run() {
        List<VirtualFile> filesToScan;
        // all non-excluded files of the project
        filesToScan = VfUtil.flattenFiles(new VirtualFile[]{project.getBaseDir()});
        filesToScan = VfUtil.filterOnlyPythonProjectFiles(project, filesToScan);

        project.getComponent(PylintPlugin.class).asyncScanFiles(filesToScan);
    }
}
