/*
 * Copyright 2013 the original author or authors.
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
package org.gradle.nativeplatform.internal;

import com.google.common.base.Joiner;
import org.gradle.nativeplatform.NativeLibraryRequirement;

// TODO: Replace this with DependencySpec
public class ProjectNativeLibraryRequirement implements NativeLibraryRequirement {
    private final String projectPath;
    private final String libraryName;
    private final String linkage;

    public ProjectNativeLibraryRequirement(String libraryName, String linkage) {
        this.projectPath = null;
        this.libraryName = libraryName;
        this.linkage = linkage;
    }

    public ProjectNativeLibraryRequirement(String projectPath, String libraryName, String linkage) {
        this.projectPath = projectPath;
        this.libraryName = libraryName;
        this.linkage = linkage==null ? "shared" : linkage;
    }

    @Override
    public String getProjectPath() {
        return projectPath;
    }

    @Override
    public String getLibraryName() {
        return libraryName;
    }

    @Override
    public String getLinkage() {
        return linkage;
    }


    @Override
    public String getVariant() {
        return linkage;
    }

    @Override
    public String getDisplayName() {
        if (projectPath==null) {
            return Joiner.on(' ').join(
                "library '" + getLibraryName() + "'",
                "linkage '" + getVariant() + "'");
        }
        return Joiner.on(' ').join(
            "project '" + getProjectPath() + "'",
            "library '" + getLibraryName() + "'",
            "linkage '" + getVariant() + "'");
    }
}
