package org.familytree.views;

import org.familytree.controllers.ApplicationController;
import org.familytree.models.DependencyGraph;
import org.familytree.services.impl.DependencyGraphServiceImpl;
import org.familytree.services.impl.NodeServiceImpl;

public class Application {
  ApplicationController applicationController;

  public void run() {
    //showMenu
  }

  public Application() {
    DependencyGraph dependencyGraph = DependencyGraph.getInstance();
    applicationController = new ApplicationController(
        new DependencyGraphServiceImpl(dependencyGraph), new NodeServiceImpl());
  }

  public void showMenu() {

  }

  public void selectOption() {

  }

  public void addNewNode() {

  }

  public void addNewDependency() {

  }

  public void deleteNode() {

  }

  public void deleteDependency() {

  }

  public void showImmediateParents() {

  }

  public void showImmediateChildren() {

  }

  public void showAncestors() {
  }

  public void showDescendants() {
  }

  public void terminate() {

  }

  public void showErrors(String error) {

  }
}
