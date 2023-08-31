package ProgettoLaboratorioB.Serializables;

import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Song implements Serializable, Comparable<Song>, ObservableList<Song> {

    static final long serialVersionUID = 1L;
    private String title;
    private String artist;
    private int year;
    private String ID;


    public Song(int year, String id, String artist, String title){
        this.title = title;
        this.artist = artist;
        this.year = year;
        this.ID = id;
    }

    public Song() {}

    public String getTitle(){
        return this.title;
    }

    public String getArtist(){
        return this.artist;
    }

    public int getYear(){
        return this.year;
    }

    public String getID(){
        return this.ID;
    }

    public void SetTitle(String title){
        this.title = title;
    }

    public void SetArtist(String artist){
        this.artist = artist;
    }

    public void SetYear(int year){
        this.year = year;
    }


    @Override
    public String toString() {
        return "Song{" +
                "title='" + getTitle() + '\'' +
                ", artist='" + getArtist() + '\'' +
                ", year=" + getYear() +
                ", ID='" + getID() + '\'' +
                '}';
    }

    @Override
    public int compareTo(Song song) { return 0;}

    public void addListener(ListChangeListener<? super Song> listChangeListener) {

    }

    public void removeListener(ListChangeListener<? super Song> listChangeListener) {

    }

    public boolean addAll(Song... songs) {
        return false;
    }

    public boolean setAll(Song... songs) {
        return false;
    }

    public boolean setAll(Collection<? extends Song> collection) {
        return false;
    }

    public boolean removeAll(Song... songs) {
        return false;
    }

    public boolean retainAll(Song... songs) {
        return false;
    }

    public void remove(int i, int i1) {

    }

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean contains(Object o) {
        return false;
    }

    public Iterator<Song> iterator() {
        return null;
    }

    public Object[] toArray() {
        return new Object[0];
    }

    public <T> T[] toArray(T[] ts) {
        return null;
    }

    public boolean add(Song song) {
        return false;
    }

    public boolean remove(Object o) {
        return false;
    }

    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    public boolean addAll(Collection<? extends Song> collection) {
        return false;
    }

    public boolean addAll(int i, Collection<? extends Song> collection) {
        return false;
    }

    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    public void clear() {

    }

    public Song get(int i) {
        return null;
    }

    public Song set(int i, Song song) {
        return null;
    }

    public void add(int i, Song song) {

    }

    public Song remove(int i) {
        return null;
    }

    public int indexOf(Object o) {
        return 0;
    }

    public int lastIndexOf(Object o) {
        return 0;
    }

    public ListIterator<Song> listIterator() {
        return null;
    }

    public ListIterator<Song> listIterator(int i) {
        return null;
    }

    public List<Song> subList(int i, int i1) {
        return null;
    }

    public void addListener(InvalidationListener invalidationListener) {

    }

    public void removeListener(InvalidationListener invalidationListener) {

    }
}
