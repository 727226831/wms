package com.lkrh.storescontrol.databinding;
import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityPutListBindingImpl extends ActivityPutListBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.l_title, 1);
        sViewsWithIds.put(R.id.l_scan, 2);
        sViewsWithIds.put(R.id.l_plate, 3);
        sViewsWithIds.put(R.id.tv_title1, 4);
        sViewsWithIds.put(R.id.iv_plate, 5);
        sViewsWithIds.put(R.id.et_plate, 6);
        sViewsWithIds.put(R.id.iv_clear, 7);
        sViewsWithIds.put(R.id.rl_update, 8);
        sViewsWithIds.put(R.id.tv_title3, 9);
        sViewsWithIds.put(R.id.iv_updatecwhcode, 10);
        sViewsWithIds.put(R.id.et_updatecwhcode, 11);
        sViewsWithIds.put(R.id.tv_total, 12);
        sViewsWithIds.put(R.id.b_submit, 13);
        sViewsWithIds.put(R.id.rv_list, 14);
    }
    // views
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityPutListBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds));
    }
    private ActivityPutListBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[13]
            , (android.widget.EditText) bindings[6]
            , (android.widget.EditText) bindings[11]
            , (android.widget.ImageView) bindings[7]
            , (android.widget.ImageView) bindings[5]
            , (android.widget.ImageView) bindings[10]
            , (android.widget.RelativeLayout) bindings[3]
            , (android.widget.LinearLayout) bindings[2]
            , (android.view.View) bindings[1]
            , (android.widget.RelativeLayout) bindings[8]
            , (androidx.recyclerview.widget.RecyclerView) bindings[14]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[12]
            );
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
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