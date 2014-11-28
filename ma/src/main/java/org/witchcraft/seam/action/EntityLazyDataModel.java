package org.witchcraft.seam.action;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.witchcraft.base.entity.BaseEntity;



public class EntityLazyDataModel<E extends BaseEntity, PK extends Serializable> extends  LazyDataModel<E>{
	
	private static final long serialVersionUID = 9151874316516949670L;
	
	BaseQuery<E, PK> entityQuery;

	public EntityLazyDataModel(BaseQuery<E, PK> baseQuery) {
		this.entityQuery = baseQuery;
	}

	@Override
	public int getRowCount() {
		return entityQuery.getResultCount().intValue();
	}

	/* (non-Javadoc)
	 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<E> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		entityQuery.setFirstResult(first);
		entityQuery.setMaxResults(pageSize);
		entityQuery.setOrderColumn(sortField);
		entityQuery.setOrderDirection(sortOrder == SortOrder.ASCENDING ? "asc" : "desc");
		List<E> result = entityQuery.getResultListTable();
		super.setWrappedData(result);
		return result;
	}

	@Override
	public Object getRowKey( E t ) {
		return t.getId();
	}

	@Override
	public E getRowData( String rowKey ) {
		if ( rowKey == null || rowKey.equals( "null" ) || entityQuery.getEntityManager() == null)
			return null;

		E t = entityQuery.getEntityManager().find( entityQuery.getEntityClass(), Long.valueOf( rowKey ) );
		return t;
	}

}
