package org.witchcraft.base.entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.ExtendedDataModel;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;
import org.jboss.seam.framework.EntityQuery;
import org.jboss.seam.log.Log;
import org.jboss.seam.log.Logging;
import org.richfaces.model.Arrangeable;
import org.richfaces.model.ArrangeableState;

public class EntityQueryDataModel<T, K> extends ExtendedDataModel<T> implements Arrangeable{

    private K rowKey;
    private EntityQuery<T> dataProvider;
    private Map<K, T> wrappedData = new HashMap<K, T>();
    private Class<T> itemClass;
    private int rowCount = -1;
    protected Log log = Logging.getLog(EntityQueryDataModel.class);

    /*
    public static EntityQueryDataModel getInstance(EntityQuery query) {
        return new EntityQueryDataModel(query, itemClass);
    }*/

    @SuppressWarnings("unchecked")
    public EntityQueryDataModel(EntityQuery<T> query, Class<T> itemClass) {
    	
        dataProvider = query;
        this.itemClass = itemClass;
        
    }

    @SuppressWarnings("unchecked")
    public void setRowKey(Object o) {
        rowKey = (K) o;
    }

    public Object getRowKey() {
        return rowKey;
    }

    @Override
    public void walk(FacesContext facesContext, DataVisitor dataVisitor, Range range, Object o) {
        int firstRow = ((SequenceRange) range).getFirstRow();
        int numberOfRows = ((SequenceRange) range).getRows();
        log.trace("Walking over #1 rows from #0", firstRow, numberOfRows);
        List<K> wrappedKeys = new ArrayList<K>();
        if (dataProvider.getFirstResult() == null || dataProvider.getFirstResult() != firstRow) {
            dataProvider.setFirstResult(firstRow);
        }
        if (dataProvider.getMaxResults() == null || dataProvider.getMaxResults() != numberOfRows) {
            dataProvider.setMaxResults(numberOfRows);
        }
        int i = 0;
        for (T item : dataProvider.getResultList()) {
            log.trace("Retrived item #0", i++);
            K id = getId(item);
            wrappedKeys.add(id);
            wrappedData.put(id, item);
            dataVisitor.process(facesContext, id, o);
        }
        log.trace("Walking done");
    }

    public boolean isRowAvailable() {
        if (getRowKey() == null) {
            return false;
        } else {
            log.trace("Checking if row with id #0 is available", getRowKey());
            return null != wrappedData.get(getRowKey());
        }
    }

    @Override
    public int getRowCount() {
        log.trace("Getting results count");
        //if (rowCount == -1) {
            rowCount = dataProvider.getResultCount().intValue();
        //}
        log.trace("Results count is #0", rowCount);
        return rowCount;
    }

    public T getRowData() {
        if (getRowKey() == null) {
            return null;
        } else {
            T item = wrappedData.get(getRowKey());
            if (item == null) {
                log.trace("Row data for key #0 not found in cache. Retriving from data provider.", getRowKey());
                item = getCurrentItem();
                wrappedData.put((K) getRowKey(), item);
                return item;
            } else {
                log.trace("Row data found in cache as #0", item);
                return item;
            }
        }
    }

    public int getRowIndex() {
        throw new UnsupportedOperationException();
    }

    public void setRowIndex(int i) {
        throw new UnsupportedOperationException();
    }

    public Object getWrappedData() {
        throw new UnsupportedOperationException();
    }

    public void setWrappedData(Object o) {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("unchecked")
    protected K getId(T item) {
       return null;
    }

    private T getCurrentItem() {
        log.trace("Fetching current row item for key #0", getRowKey());
        return dataProvider.getEntityManager().find(itemClass, getRowKey());
    }

	@Override
	public void arrange(FacesContext arg0, ArrangeableState arg1) {
		// TODO Auto-generated method stub
		
	}
}