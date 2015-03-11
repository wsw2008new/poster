package org.pti.poster.repository.post;

public enum PostRepositoryType {
	INMEMORY;

	private String typeName;

	private PostRepositoryType() {
		this.typeName = name().replaceAll("_", ".").toLowerCase();
	}

	public String getTypeName() {
		return typeName;
	}

	@Override
	public String toString() {
		return typeName;
	}
}
