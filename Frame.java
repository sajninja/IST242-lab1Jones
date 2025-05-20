class Frame {
    private Material material;
    private Size frameType;

    public Frame(Material material, Size frameType) {
        this.material = material;
        this.frameType = frameType;
    }

    public void setFrameType(int index) {
        frameType = Size.values()[index - 1];
    }

    public Size getFrameType() { return frameType; }

    public void setFrameMaterial(int index) {
        material = Material.values()[index - 1];
    }

    public Material getFrameMaterial() { return material; }
}
