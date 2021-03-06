package au.edu.utas.costing_tool.Model;


// =============================================================================
// External Imports
// =============================================================================

import javax.persistence.Embeddable;


// TODO(Andrew): Consider making this a separate table
@Embeddable
public class FORCodes
{
    // =========================================================================
    // Properties
    // =========================================================================

    private Integer code1;
    public Integer getCode1() {return this.code1;}
    public void setCode1(Integer code) {this.code1 = code;}

    private Double percent1;
    public Double getPercent1() {return this.percent1;}
    public void setPercent1(Double percent) {this.percent1 = percent;}

    private Integer code2;
    public Integer getCode2() {return this.code2;}
    public void setCode2(Integer code) {this.code2 = code;}

    private Double percent2;
    public Double getPercent2() {return this.percent2;}
    public void setPercent2(Double percent) {this.percent2 = percent;}

    private Integer code3;
    public Integer getCode3() {return this.code3;}
    public void setCode3(Integer code) {this.code3 = code;}

    private Double percent3;
    public Double getPercent3() {return this.percent3;}
    public void setPercent3(Double percent) {this.percent3 = percent;}


    // =========================================================================
    // Constructors
    // =========================================================================

    public FORCodes() {}

    public FORCodes(Integer code1,
                    Double percent1,
                    Integer code2,
                    Double percent2,
                    Integer code3,
                    Double percent3)
    {
        this.setCode1(code1);
        this.setPercent1(percent1);

        this.setCode2(code2);
        this.setPercent2(percent2);

        this.setCode3(code3);
        this.setPercent3(percent3);
    }
}