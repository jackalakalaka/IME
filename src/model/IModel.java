package model;

public interface IModel {

  ImageModel getImageFromModel(String imageName);

  void addImage(String name,ImageModel imageModel);

}
