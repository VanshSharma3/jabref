package org.jabref.gui;

import java.util.List;

import javafx.collections.ObservableList;

import org.jabref.model.database.BibDatabaseContext;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
public interface LibraryTabContainer {
    ObservableList<LibraryTab> getLibraryTabs();

    @Nullable
    LibraryTab getCurrentLibraryTab();

    void showLibraryTab(LibraryTab libraryTab);

    void addTab(BibDatabaseContext bibDatabaseContext, boolean raisePanel);

    void addTab(LibraryTab libraryTab, boolean raisePanel);

    /// Closes a designated libraryTab
    ///
    /// @param tab to be closed.
    /// @return true if closing the tab was successful
    boolean closeTab(@Nullable LibraryTab tab);

    boolean closeTabs(List<LibraryTab> tabs);

    /// Refreshes the ui after changes to the preferences
    void refresh();
    default void sortTabsAlphabetically() {
        getLibraryTabs().sort((tab1, tab2) -> {
            String title1 = tab1.getBibDatabaseContext().getDatabasePath()
                    .map(path -> path.getFileName().toString())
                    .orElse("Untitled");
            String title2 = tab2.getBibDatabaseContext().getDatabasePath()
                    .map(path -> path.getFileName().toString())
                    .orElse("Untitled");
            return title1.compareToIgnoreCase(title2);
        });
    }
}
