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

package com.leinardi.pycharm.pylint;

import com.intellij.CommonBundle;
import com.intellij.reference.SoftReference;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.PropertyKey;

import java.lang.ref.Reference;
import java.util.ResourceBundle;

public final class PylintBundle {

    private static Reference<ResourceBundle> bundleReference;

    @NonNls
    private static final String BUNDLE = "com.leinardi.pycharm.pylint.PylintBundle";

    private PylintBundle() {
        // utility class
    }

    public static String message(@PropertyKey(resourceBundle = BUNDLE) final String key, final Object... params) {
        return CommonBundle.message(getBundle(), key, params);
    }

    private static ResourceBundle getBundle() {
        ResourceBundle bundle = null;

        if (bundleReference != null) {
            bundle = bundleReference.get();
        }

        if (bundle == null) {
            bundle = ResourceBundle.getBundle(BUNDLE);
            bundleReference = new SoftReference<>(bundle);
        }

        return bundle;
    }

}
