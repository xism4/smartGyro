package android.support.v7.view.menu;

class BaseWrapper<T> {
    final T mWrappedObject;

    BaseWrapper(Object obj) {
        if (obj != null) {
            this.mWrappedObject = obj;
            return;
        }
        throw new IllegalArgumentException("Wrapped Object can not be null.");
    }
}
