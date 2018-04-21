package com.wijkuy.imagecoloranalysis.images.images.images.generated;

import com.speedment.common.annotation.GeneratedCode;
import com.speedment.common.injector.annotation.ExecuteBefore;
import com.speedment.common.injector.annotation.WithState;
import com.speedment.runtime.config.identifier.TableIdentifier;
import com.speedment.runtime.core.component.sql.SqlPersistenceComponent;
import com.speedment.runtime.core.component.sql.SqlStreamSupplierComponent;
import com.speedment.runtime.core.exception.SpeedmentException;
import com.wijkuy.imagecoloranalysis.images.images.images.Images;
import com.wijkuy.imagecoloranalysis.images.images.images.ImagesImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import static com.speedment.common.injector.State.RESOLVED;

/**
 * The generated Sql Adapter for a {@link
 * com.wijkuy.imagecoloranalysis.images.images.images.Images} entity.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public abstract class GeneratedImagesSqlAdapter {
    
    private final TableIdentifier<Images> tableIdentifier;
    
    protected GeneratedImagesSqlAdapter() {
        this.tableIdentifier = TableIdentifier.of("images", "images", "images");
    }
    
    @ExecuteBefore(RESOLVED)
    void installMethodName(@WithState(RESOLVED) SqlStreamSupplierComponent streamSupplierComponent,
            @WithState(RESOLVED) SqlPersistenceComponent persistenceComponent) {
        streamSupplierComponent.install(tableIdentifier, this::apply);
        persistenceComponent.install(tableIdentifier);
    }
    
    protected Images apply(ResultSet resultSet) throws SpeedmentException {
        final Images entity = createEntity();
        try {
            entity.setId(     resultSet.getInt(1)    );
            entity.setUrl(    resultSet.getString(2) );
            entity.setColor1( resultSet.getString(3) );
            entity.setColor2( resultSet.getString(4) );
            entity.setColor3( resultSet.getString(5) );
        } catch (final SQLException sqle) {
            throw new SpeedmentException(sqle);
        }
        return entity;
    }
    
    protected ImagesImpl createEntity() {
        return new ImagesImpl();
    }
}