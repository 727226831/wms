package com.lkrh.storescontrol.databinding;
import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityLogisticsDistributionBindingImpl extends ActivityLogisticsDistributionBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.rl_cwhcode, 2);
        sViewsWithIds.put(R.id.tv_title1, 3);
        sViewsWithIds.put(R.id.iv_plate, 4);
        sViewsWithIds.put(R.id.et_plate, 5);
        sViewsWithIds.put(R.id.iv_clear, 6);
        sViewsWithIds.put(R.id.l_batch, 7);
        sViewsWithIds.put(R.id.tv_cvenbatch, 8);
        sViewsWithIds.put(R.id.ib_upload, 9);
        sViewsWithIds.put(R.id.b_depart, 10);
        sViewsWithIds.put(R.id.b_arrive, 11);
    }
    // views
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityLogisticsDistributionBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }
    private ActivityLogisticsDistributionBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[11]
            , (android.widget.Button) bindings[10]
            , (android.widget.EditText) bindings[5]
            , (android.widget.ImageButton) bindings[9]
            , (android.widget.ImageView) bindings[6]
            , (android.widget.ImageView) bindings[4]
            , (android.widget.LinearLayout) bindings[7]
            , (android.widget.RelativeLayout) bindings[2]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[3]
            );
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}