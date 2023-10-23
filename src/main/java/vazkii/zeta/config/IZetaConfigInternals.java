package vazkii.zeta.config;

public interface IZetaConfigInternals {
	<T> T get(ValueDefinition<T> definition);
	<T> void set(ValueDefinition<T> definition, T value);

	void refresh(Definition thing);
}
