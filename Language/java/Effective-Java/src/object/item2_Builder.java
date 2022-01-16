package object;

public class item2_Builder {
	
//	Book effJava = new Book("Effective Java", "Joshua Bloch", 120, 100, 500);
	Book effJava = new Book.Builder("Effective Java", "Joshua Bloch").setHeight(120).setWidth(100).setPage(500).build();

}

class Book {
	private final String name;
	private final String author;

	private volatile int sizeHeight;
	private volatile int sizeWidth;
	private volatile int totalPage;
	
//	public Book (String name, String author, int sizeHeight, int sizeWidth, int totalPage) {
//		this.name = name;
//		this.author = author;
//		this.sizeHeight = sizeHeight;
//		this.sizeWidth = sizeWidth;
//		this.totalPage = totalPage;
//	}
//	
//	public Book (String author, String name) {
//		this(author, name, 0, 0, 0);
//	}
	
	private Book(Builder builder) {
		name = builder.name;
		author = builder.author;

		sizeHeight = builder.sizeHeight;
		sizeWidth = builder.sizeWidth;
		totalPage = builder.totalPage;
	}
	
	public static class Builder {
		private final String name;
		private final String author;

		private int sizeHeight = 0;
		private int sizeWidth = 0;
		private int totalPage = 0;
		
		public Builder(String name, String author) {
			this.name = name;
			this.author = author;
		}
		
		public Builder setHeight(int height) {
			this.sizeHeight = height;
			return this;
		}

		public Builder setWidth(int width) {
			this.sizeWidth = width;
			return this;
		}

		public Builder setPage(int page) {
			this.totalPage = page;
			return this;
		}

		public Book build() {
			return new Book(this);
		}
	}
}